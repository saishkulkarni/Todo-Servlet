<!DOCTYPE html>
<%@page import="dto.Task"%>
<%@page import="dao.MyDao"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Task | Todo App</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }

        .container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 100%;
            max-width: 500px;
        }

        h1 {
            color: #1877f2;
            font-size: 28px;
            margin-bottom: 20px;
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        fieldset {
            border: none;
            padding: 0;
        }

        legend {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 15px;
        }

        .input-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #606770;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #dddfe2;
            border-radius: 4px;
            font-size: 16px;
        }

        .button-group {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        button {
            background-color: #1877f2;
            border: none;
            border-radius: 4px;
            color: #ffffff;
            cursor: pointer;
            font-size: 16px;
            padding: 10px 20px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #166fe5;
        }

        button[type="reset"] {
            background-color: #e4e6eb;
            color: #050505;
        }

        button[type="reset"]:hover {
            background-color: #d8dadf;
        }

        .back-button {
            display: block;
            text-align: center;
            margin-top: 20px;
        }

        .back-button button {
            background-color: #42b72a;
        }

        .back-button button:hover {
            background-color: #36a420;
        }
    </style>
</head>
<body>
    <div class="container">
        <%
        int id = Integer.parseInt(request.getParameter("id"));
        MyDao dao = new MyDao();
        Task task = dao.findById(id);
        %>
        <h1>Edit Task</h1>
        <form action="update-task" method="post">
            <input type="hidden" name="id" value="<%=task.getId()%>">
            <fieldset>
                <legend>Enter Task here</legend>
                <div class="input-group">
                    <label for="tname">Task Name:</label>
                    <input type="text" id="tname" name="tname" value="<%=task.getName()%>" required>
                </div>
                <div class="input-group">
                    <label for="tdescription">Task Description:</label>
                    <input type="text" id="tdescription" name="tdescription" value="<%=task.getDescription()%>" required>
                </div>
                <div class="button-group">
                    <button type="submit">Update</button>
                    <button type="reset">Cancel</button>
                </div>
            </fieldset>
        </form>
        <div class="back-button">
            <a href="Home.jsp"><button type="button">Back</button></a>
        </div>
    </div>
</body>
</html>
