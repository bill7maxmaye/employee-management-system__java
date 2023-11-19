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


@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String designation = request.getParameter("designation");
            double salary = Double.parseDouble(request.getParameter("salary"));

            Employee newEmployee = new Employee();
            newEmployee.setName(name);
            newEmployee.setDesignation(designation);
            newEmployee.setSalary(salary);

            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO employees (name, designation, salary) VALUES (?, ?, ?)");

            preparedStatement.setString(1, newEmployee.getName());
            preparedStatement.setString(2, newEmployee.getDesignation());
            preparedStatement.setDouble(3, newEmployee.getSalary());

            int rowsAffected = preparedStatement.executeUpdate();

            connection.close();

            if (rowsAffected > 0) {
                // User added successfully
                response.sendRedirect("viewEmployees");
            } else {
            	System.out.println("Error: User addition failed. No rows affected.");
                // User addition failed
                response.sendRedirect("addEmployeeForm.html?error=true");
                // You can handle errors more effectively by redirecting to an error page or displaying a message on the same page.
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Exception occurred during user addition.");
            response.sendRedirect("addEmployeeForm.html?error=true");
        }
    }
}

