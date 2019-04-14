package asd.demo.controller;

import asd.demo.model.User;
import asd.demo.model.Users;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBException;

/**
 *
 * @author George
 */
public class ValidationServlet extends HttpServlet {

    @Context
    private ServletContext application;

    private UserApplication getUserApp() throws JAXBException, IOException, Exception {
        synchronized (application) {
            UserApplication userApp = (UserApplication) application.getAttribute("userApp");
            if (userApp == null) {
                userApp = new UserApplication();
                userApp.setFilePath(application.getRealPath("WEB-INF/users.xml"));
                application.setAttribute("userApp", userApp);
            }
            return userApp;
        }
    }

    private Users users() throws IOException, Exception {
        return getUserApp().getUsers();
    }

    private User user(String email, String password) throws Exception {
        return users().login(email, password);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String emailErr;
        String passErr;
        String nameErr;
        String phoneErr;
        String empty;

        Validator validator = new Validator();
        User user;
        try {
            user = user(email, password);
        } catch (Exception ex) {
            user = null;
            Logger.getLogger(ValidationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (validator.checkEmpty(email, password)) {
            request.setAttribute("emptyErr", "All fields are mendatory");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (!validator.validateEmail(email)) {
            request.setAttribute("emailErr", "Incorrect email format");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (!validator.validatePassword(password)) {
            request.setAttribute("passErr", "Incorrect password format");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (!validator.validatePassword(name)) {
            request.setAttribute("nameErr", "Incorrect name format");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (!validator.validatePassword(phone)) {
            request.setAttribute("passErr", "Incorrect phone format");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (user != null) {
            request.getRequestDispatcher("main.jsp").forward(request, response);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);            
        } else {
            request.setAttribute("existErr", "User does not exist!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
