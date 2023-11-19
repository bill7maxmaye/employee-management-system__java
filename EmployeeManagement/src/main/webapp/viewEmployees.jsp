<%@ page import="java.util.List" %>
<%@ page import="bill.com.Employee" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Management System</title>
</head>
<body>

<div class="container mt-5">
    <h2>Employee List</h2>
    <table class="table" id="employeeTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Designation</th>
            <th>Salary</th>
        </tr>
        </thead>
        <tbody id="employeeTableBody">
            <% 
                List<Employee> employees = (List<Employee>)request.getAttribute("employees");
                Iterator<Employee> iterator = employees.iterator();
                while(iterator.hasNext()) {
                    Employee employee = iterator.next();
            %>
                <tr>
                    <td><%= employee.getId() %></td>
                    <td><%= employee.getName() %></td>
                    <td><%= employee.getDesignation() %></td>
                    <td><%= employee.getSalary() %></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
