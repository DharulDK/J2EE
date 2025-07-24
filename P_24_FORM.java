package P_24_FORM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EmployeeServlet")
public class P_4_FORM extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read form data from request parameters
        String empNo = request.getParameter("empNo");
        String empName = request.getParameter("empName");
        String designation = request.getParameter("designation");
        String qualification = request.getParameter("qualification");

        // Set response content type
        response.setContentType("text/html");

        // Output response
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Employee Details</h2>");
        response.getWriter().println("<p><strong>Employee Number:</strong> " + empNo + "</p>");
        response.getWriter().println("<p><strong>Employee Name:</strong> " + empName + "</p>");
        response.getWriter().println("<p><strong>Designation:</strong> " + designation + "</p>");
        response.getWriter().println("<p><strong>Qualifications:</strong> " + qualification + "</p>");
        response.getWriter().println("</body></html>");
    }
}




employee.html

<!DOCTYPE html>
<html>
<head>
    <title>Employee Form</title>
</head>
<body>
    <h2>Enter Employee Details</h2>
    <form action="EmployeeServlet" method="get">
        <label for="empNo">Employee Number:</label>
        <input type="text" id="empNo" name="empNo" required /><br/><br/>

        <label for="empName">Employee Name:</label>
        <input type="text" id="empName" name="empName" required /><br/><br/>

        <label for="designation">Designation:</label>
        <input type="text" id="designation" name="designation" required /><br/><br/>

        <label for="qualification">Qualifications:</label>
        <input type="text" id="qualification" name="qualification" required /><br/><br/>

        <input type="submit" value="Submit" />
    </form>
</body>
</html>
