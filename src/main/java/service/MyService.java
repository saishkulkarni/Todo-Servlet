package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Customer;
import dto.Task;
import helper.AES;

public class MyService {

	MyDao dao = new MyDao();

	public void saveUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));

		int age = LocalDate.now().getYear() - dob.getYear();

		if (age < 18) {
			resp.getWriter().print(
					"<h1 align='center' style='color:red'>Sorry! You are not eligible for Creating Account</h1>");
			req.getRequestDispatcher("signup.html").include(req, resp);
		} else {

			Customer customer2 = dao.findByEmail(email);

			if (customer2 == null) {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setAge(age);
				customer.setDob(dob);
				customer.setEmail(email);
				customer.setGender(gender);
				customer.setMobile(mobile);
				customer.setPassword(AES.encrypt(password, "123"));

				dao.saveCustomer(customer);

				resp.getWriter().print("<h1 align='center' style='color:green'>Account Created Success </h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			} else {
				resp.getWriter().print("<h1 align='center' style='color:red'>Account Already Exists with the Email : "
						+ email + " </h1>");
				req.getRequestDispatcher("signup.html").include(req, resp);
			}
		}

	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Customer customer = dao.findByEmail(email);

		if (customer == null) {
			resp.getWriter().print("<h1 align='center' style='color:red'>Invalid Email</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			if (password.equals(AES.decrypt(customer.getPassword(), "123"))) {
				req.getSession().setAttribute("customer", customer);
				req.getSession().setMaxInactiveInterval(30);
				resp.getWriter().print("<h1 align='center' style='color:green'>Login Success</h1>");

				List<Task> tasks = dao.fetchTasks(customer.getId());
				req.setAttribute("tasks", tasks);

				req.getRequestDispatcher("home.jsp").include(req, resp);
			} else {
				resp.getWriter().print("<h1 align='center' style='color:red'>Invalid Password</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		}
	}

	public void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String name = req.getParameter("tname");
		String description = req.getParameter("tdescription");

		Task task = new Task();
		task.setName(name);
		task.setDescription(description);
		task.setCreatedTime(LocalDateTime.now());

		Customer customer = (Customer) req.getSession().getAttribute("customer");

		task.setCustomer(customer);

		dao.saveTask(task);
		resp.getWriter().print("<h1 align='center' style='color:green'>Task Added Success</h1>");

		List<Task> tasks = dao.fetchTasks(customer.getId());
		req.setAttribute("tasks", tasks);

		req.getRequestDispatcher("home.jsp").include(req, resp);

	}

	public void complete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Task task = dao.findById(id);
		task.setStatus(true);
		dao.updateTask(task);

		resp.getWriter().print("<h1 align='center' style='color:green'>Status Changed Success</h1>");
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		List<Task> tasks = dao.fetchTasks(customer.getId());
		req.setAttribute("tasks", tasks);

		req.getRequestDispatcher("home.jsp").include(req, resp);
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Task task = dao.findById(id);

		dao.deleteTask(task);

		resp.getWriter().print("<h1 align='center' style='color:green'>Task Deleted Success</h1>");
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		List<Task> tasks = dao.fetchTasks(customer.getId());
		req.setAttribute("tasks", tasks);

		req.getRequestDispatcher("home.jsp").include(req, resp);
	}

	public void updateTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("tname");
		String description = req.getParameter("tdescription");
		int id = Integer.parseInt(req.getParameter("id"));
		Customer customer = (Customer) req.getSession().getAttribute("customer");

		Task task = new Task();
		task.setId(id);
		task.setName(name);
		task.setDescription(description);
		task.setCreatedTime(LocalDateTime.now());
		task.setStatus(false);
		task.setCustomer(customer);

		dao.updateTask(task);

		List<Task> tasks = dao.fetchTasks(customer.getId());
		req.setAttribute("tasks", tasks);
		resp.getWriter().print("<h1 align='center' style='color:green'>Task Updated Success</h1>");

		req.getRequestDispatcher("home.jsp").include(req, resp);
	}
}
