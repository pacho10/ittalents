package bg.ittalents.efficientproject.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bg.ittalents.efficientproject.model.exception.DBException;
import bg.ittalents.efficientproject.model.exception.EfficientProjectDAOException;
import bg.ittalents.efficientproject.model.interfaces.DAOStorageSourse;
import bg.ittalents.efficientproject.model.interfaces.IProjectDAO;
import bg.ittalents.efficientproject.model.interfaces.IUserDAO;
import bg.ittalents.efficientproject.model.pojo.User;

/**
 * Servlet implementation class ReturnTasksPerState
 */
@WebServlet("/returnTasksPerState")
public class ReturnTasksPerStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DAOStorageSourse SOURCE_DATABASE = DAOStorageSourse.DATABASE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");

			/**
			 * check if there is no session or there is but the user is different or the
			 * user is not admin:
			 */
			if (request.getSession(false) == null || request.getSession().getAttribute("user") != null) {
				response.sendRedirect("./LogIn");
				return;
			}
				User user = (User) request.getSession().getAttribute("user");

				if (!user.isAdmin()) {
					request.getRequestDispatcher("errorNotAuthorized.jsp").forward(request, response);
					return;
				}
				int projectId = Integer.parseInt(request.getParameter("projectId"));
				String tasksPerStateJSON = new Gson()
						.toJson(IProjectDAO.getDAO(SOURCE_DATABASE).tasksNumberPerState(projectId));
				System.out.println(tasksPerStateJSON);
				response.getWriter().println(tasksPerStateJSON);
		} catch (IOException | ServletException | DBException | EfficientProjectDAOException e) {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IOException | ServletException e1) {
				e1.printStackTrace();
			}
		}
	}
}
