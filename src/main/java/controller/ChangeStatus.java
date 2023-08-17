package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.User;

@WebServlet("/changestatus")
public class ChangeStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			// Logic to Fetch Task Object
			int id = Integer.parseInt(req.getParameter("id"));
			UserDao dao = new UserDao();
			Task task = dao.fetchTask(id);

			if (task == null) {
				resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			} else {

				// Logic to Change Status
				if (task.isStatus())
					task.setStatus(false);
				else
					task.setStatus(true);

				// Logic to Update in Database
				dao.update(task);

				// Logic to Update Session
				User user2 = dao.fetchByEmail(user.getEmail());
				req.getSession().setAttribute("user", user2);

				resp.getWriter().print("<h1 style='color:green'>Status Changed Successfully</h1>");

				req.setAttribute("list", user2.getTasks());
				req.getRequestDispatcher("Home.jsp").include(req, resp);
			}

		}
	}

}
