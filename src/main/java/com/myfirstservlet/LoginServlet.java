package com.myfirstservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
* This Annotation is used to declare the servlet.
 */
@WebServlet (
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Moonlight"),
                @WebInitParam(name = "password", value = "Kingdom")
        }
)

/**
 * Creating LoginServlet class which extend the HttpServlet abstract class which contain the methods
 * that will be override in this LoginServlet class.
 */
public class LoginServlet extends HttpServlet {
    /**
     * by using this doPost method servlet handle the request and the response.
     * @param req;
     * @param resp;
     * @throws ServletException;
     * @throws IOException;
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get request parameters for userID and password
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        //get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        /*
         *  Validating of UserName by using java Regular Expression.
         *  User Name start with the Capital letter.
         *  It has minimum three character.
         */
        String regexName = "^[A-Z]{1}[a-zA-Z]{2,}$";

        if(userID.equals(user) && userID.matches(regexName) && password.equals(pwd)) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Either username or password is incorrect!</font>");
            rd.include(req, resp);
        }
    }
}
