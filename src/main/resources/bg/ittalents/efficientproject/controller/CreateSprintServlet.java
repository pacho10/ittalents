package bg.ittalents.efficientproject.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ittalents.efficientproject.model.exception.DBException;
import bg.ittalents.efficientproject.model.interfaces.DAOStorageSourse;
import bg.ittalents.efficientproject.model.interfaces.ISprintDAO;
import bg.ittalents.efficientproject.model.pojo.Sprint;

/**
 * Servlet implementation class CreateSprintServlet
 */
@WebServlet("/createsprint")
public class CreateSprintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSprintServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectId= Integer.parseInt(request.getParameter("projectId"));
		request.setAttribute("projectId", projectId);
		request.getRequestDispatcher("./createSprint.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			ServletContext context = getServletContext();
			boolean isExpired = true;
			if (context.getAttribute("sprintId") != null) {
				int id = (int) request.getSession().getAttribute("user");
				try {
					Sprint sprint = ISprintDAO.getDAO(DAOStorageSourse.DATABASE).getSprintBId(id);
					//int days = sprint.getStartDate(). + sprint.getDuration();
					int test = sprint.getStartDate().compareTo(new Date());
					System.out.println(test);
				} catch (DBException e) {
					e.printStackTrace();
					response.sendRedirect("./error.jsp");
				}
			}
			
			String name = request.getParameter("name");
			int duration = Integer.parseInt(request.getParameter("duration"));
		    int projectId= Integer.parseInt(request.getParameter("projectId"));
		    
			Sprint sprintToAdd = new Sprint(name, duration,projectId);
			try {
				int id = ISprintDAO.getDAO(DAOStorageSourse.DATABASE).createSprint(sprintToAdd);
				context.setAttribute("sprintId", id);
				System.out.println(context.getAttribute("sprintId"));
			} catch (DBException e) {
				e.printStackTrace();
				response.sendRedirect("./error.jsp");
			}
		} else {
			response.sendRedirect("./LogIn");
			}
		String name = request.getParameter("name");
		int duration = Integer.parseInt(request.getParameter("duration"));
		int projectId= Integer.parseInt(request.getParameter("projectId"));
//		request.setAttribute("projectId", projectId);
		
		
		Sprint sprintToAdd = new Sprint(name, duration,projectId);
		try {
			ISprintDAO.getDAO(DAOStorageSourse.DATABASE).createSprint(sprintToAdd);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		//TODO
		response.sendRedirect("./projectdetail?projectId="+projectId);
//		RequestDispatcher rd = request.getRequestDispatcher("./profileShow.jsp");
//		rd.forward(request, response);
	}
}
