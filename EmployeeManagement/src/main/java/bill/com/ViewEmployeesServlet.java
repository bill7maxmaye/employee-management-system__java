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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/viewEmployees")
public class ViewEmployeesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees");
            ResultSet resultSet = preparedStatement.executeQuery();
          

            List<Employee> employees = new ArrayList<>();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setDesignation(resultSet.getString("designation"));
                employee.setSalary(resultSet.getDouble("salary"));
                employees.add(employee);
                
        }

        connection.close();

            request.setAttribute("employees", employees);
            request.getRequestDispatcher("viewEmployees.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
