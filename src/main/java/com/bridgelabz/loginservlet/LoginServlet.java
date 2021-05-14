package com.bridgelabz.loginservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet",
        urlPatterns = { "/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Ruchir"),
                @WebInitParam(name = "password", value = "Brid@Labz999")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String password = req.getParameter("pass");

        // Regular expression for proper username - 1st Letter Caps and minimum 4 chars
        String userNameRegex = "^[A-Z][a-zA-Z]{2,}$";

        // Regular expression for proper password - Should contain 1 digit, 1 lowercase, 1 uppercase and 1 special char
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*]).{8,}$";

        Pattern usernamePattern = Pattern.compile(userNameRegex);
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        // If username and password is as per expectation
        if(usernamePattern.matcher(user).matches() && passwordPattern.matcher(password).matches()){
            String paramUserId = getServletConfig().getInitParameter("user");
            String paramPassword = getServletConfig().getInitParameter("password");
            if(paramUserId.equals(user) && paramPassword.equals(password)){
                req.setAttribute("user",user);
                req.getRequestDispatcher("LoginSuccess.jsp").forward(req,resp);
            } else {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = resp.getWriter();
                out.println("<font color=red> Username or password is incorrect. Please try again.</font><br>");
                requestDispatcher.include(req,resp);
            }
        }
        // If username and password does not match expectations
        else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red> Username/Password does not match expectations.</font><br>");
            out.println(usernamePattern.matcher(user).matches());
            requestDispatcher.include(req,resp);
        }

    }
}
