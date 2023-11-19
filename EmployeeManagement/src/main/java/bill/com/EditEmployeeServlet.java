package bill.com;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/editEmployee")
public class EditEmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int employeeId = Integer.parseInt(request.getParameter("id"));

            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
            preparedStatement.setInt(1, employeeId);

            Employee existingEmployee = new Employee();

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    existingEmployee.setId(resultSet.getInt("id"));
                    existingEmployee.setName(resultSet.getString("name"));
                    existingEmployee.setDesignation(resultSet.getString("designation"));
                    existingEmployee.setSalary(resultSet.getDouble("salary"));
                }
            }

            connection.close();

            request.setAttribute("employee", existingEmployee);
            request.getRequestDispatcher("editEmployeeForm.html").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String designation = request.getParameter("designation");
            double salary = Double.parseDouble(request.getParameter("salary"));

            Employee updatedEmployee = new Employee();
            updatedEmployee.setId(id);
            updatedEmployee.setName(name);
            updatedEmployee.setDesignation(designation);
            updatedEmployee.setSalary(salary);

            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE employees SET name=?, designation=?, salary=? WHERE id=?");

            preparedStatement.setString(1, updatedEmployee.getName());
            preparedStatement.setString(2, updatedEmployee.getDesignation());
            preparedStatement.setDouble(3, updatedEmployee.getSalary());
            preparedStatement.setInt(4, updatedEmployee.getId());

            preparedStatement.executeUpdate();

            connection.close();

            response.sendRedirect("viewEmployees.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

