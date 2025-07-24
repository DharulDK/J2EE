package P_23_FORM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/WelcomeUser")
public class P_3_FORM extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WelcomeUser() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Welcome " + username + "!</h2>");
        response.getWriter().println("</body></html>");
    }
}
