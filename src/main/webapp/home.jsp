<%@page import="dto.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | Todo App</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            color: #1c1e21;
            line-height: 1.6;
            padding: 20px;
        }

        .container {
            max-width: 1000px;
            margin: 0 auto;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
        }

        h1 {
            color: #1877f2;
            font-size: 28px;
            margin-bottom: 20px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #e4e6eb;
        }

        th {
            background-color: #f5f6f7;
            font-weight: bold;
        }

        button {
            background-color: #1877f2;
            border: none;
            border-radius: 4px;
            color: #ffffff;
            cursor: pointer;
            font-size: 14px;
            padding: 8px 12px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #166fe5;
        }

        .complete-button {
            background-color: #42b72a;
        }

        .complete-button:hover {
            background-color: #36a420;
        }

        .delete-button {
            background-color: #e41e3f;
        }

        .delete-button:hover {
            background-color: #d01031;
        }

        .action-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .action-buttons a {
            text-decoration: none;
        }

        .add-task-button {
            background-color: #42b72a;
        }

        .add-task-button:hover {
            background-color: #36a420;
        }

        .logout-button {
            background-color: #e4e6eb;
            color: #050505;
        }

        .logout-button:hover {
            background-color: #d8dadf;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Todo List</h1>
        <%
        List<Task> tasks = (List<Task>) request.getAttribute("tasks");
        %>
        <table>
            <tr>
                <th>Task Name</th>
                <th>Task Description</th>
                <th>Created Time</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <% if (tasks != null) {
                for (Task task : tasks) { %>
                <tr>
                    <td><%=task.getName()%></td>
                    <td><%=task.getDescription()%></td>
                    <td><%=task.getCreatedTime()%></td>
                    <td>
                        <% if (task.isStatus()) { %>
                            Completed
                        <% } else { %>
                            <a href="/todo-app/complete?id=<%=task.getId()%>"><button class="complete-button">Complete</button></a>
                        <% } %>
                    </td>
                    <td>
                        <a href="/todo-app/delete?id=<%=task.getId()%>"><button class="delete-button">Delete</button></a>
                        <a href="/todo-app/edit-task.jsp?id=<%=task.getId()%>"><button>Edit</button></a>
                    </td>
                </tr>
            <% }
            } %>
        </table>
        <div class="action-buttons">
            <a href="/todo-app/add-task"><button class="add-task-button">Add Task</button></a>
            <a href="/todo-app/logout"><button class="logout-button">Logout</button></a>
        </div>
    </div>
</body>
</html>
