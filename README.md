# Todo Application using Servlets

This is a simple Todo application built using Java Servlets, JSP, and MySQL. It allows users to manage their tasks efficiently with features like user authentication, task creation, editing, and deletion.

## 🚀 Features

- User Registration and Login
- Add new tasks
- View all tasks
- Edit existing tasks
- Delete tasks
- Mark tasks as complete
- Responsive design for mobile and desktop

## 🛠️ Technologies Used

- Java Servlets
- JavaServer Pages (JSP)
- MySQL Database
- HTML5
- CSS3
- JDBC for database connectivity

## 📋 Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Tomcat 8 or higher
- MySQL 5.7 or higher
- Maven (for dependency management)

## 🏁 Getting Started

1. Clone the repository:

2. Set up the MySQL database:
- Create a new database named `todo_db`
- Import the SQL schema from `database/schema.sql` (if provided)

3. Configure the database connection:
- Open `src/main/java/dao/MyDao.java`
- Update the database URL, username, and password with your MySQL credentials in persistence.xml

4. Build the project:

5. Deploy the WAR file to your Tomcat server

6. Access the application at `http://localhost:8080/todo-app`

## 📁 Project Structure

todo-app/ ├── src/ │ ├── main/ │ │ ├── java/ │ │ │ ├── controller/ │ │ │ ├── dao/ │ │ │ ├── dto/ │ │ │ └── service/ │ │ └── webapp/ │ │ ├── WEB-INF/ │ │ └── *.jsp ├── pom.xml └── README.md


## 🖥️ Usage

1. Register a new account or login with existing credentials
2. Add new tasks using the "Add Task" button
3. View all tasks on the home page
4. Edit or delete tasks using the respective buttons
5. Mark tasks as complete when finished
6. Logout when done

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](https://github.com/saishkulkarni/Todo-Servlet/issues).

## 👨‍💻 Author

**Saish Kulkarni**

- GitHub: [@saishkulkarni](https://github.com/saishkulkarni)
- LinkedIn: [Saish Kulkarni](https://www.linkedin.com/in/saish-kulkarni-777a44288/)

## 📝 License

This project is [MIT](https://choosealicense.com/licenses/mit/) licensed.

## 🙏 Acknowledgements

- [Servlet Documentation](https://javaee.github.io/servlet-spec/)
- [JSP Documentation](https://javaee.github.io/javaee-spec/javadocs/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

---

Happy Task Managing! 📝✅
