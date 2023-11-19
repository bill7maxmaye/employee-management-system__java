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


@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int employeeId = Integer.parseInt(request.getParameter("id"));

            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
            preparedStatement.setInt(1, employeeId);

            preparedStatement.executeUpdate();

            connection.close();

            response.sendRedirect("viewEmployees.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

