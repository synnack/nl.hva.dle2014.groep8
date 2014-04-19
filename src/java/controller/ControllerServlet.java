package controller;

import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UserFacade;

/**
 *
 * @author wilco
 */
@WebServlet(name = "ControllerServlet",
            loadOnStartup = 1,
            urlPatterns = {"",
                           "/login", 
                           "/home",
                           "/manage",
                           "/mycompetencies",
                           "/mycourses",
                           "/knowledgebase",
                           "/lecture",
                           "/register",
                           "/forgotpassword",
                           "/logout",
                           "/courses/([0-9]+)/documents",
                           "/courses/([0-9]+)/lectures",
                           
            })
public class ControllerServlet extends HttpServlet {
    @EJB
    private UserFacade userFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
                
        String userPath;
        
        /* Handle logging in */
        if (request.getServletPath().equals("/login")) {
            User user = userFacade.findByUsername(request.getParameter("username"));
            if (user == null || !user.isPasswordCorrect(request.getParameter("password"))) {
                /* Fail! */
                request.getRequestDispatcher("/WEB-INF/view/unauth/login.jsp").forward(request, response);
                return;
            }
            session.setAttribute("User", user);
            response.sendRedirect("home");
            return;
        }
        
        /* Handle register */
        if (request.getServletPath().equals("/register")) {
            if (request.getMethod().equals("POST")) {
                boolean success = userFacade.addUser(
                        request.getParameter("username"), 
                        request.getParameter("password"),
                        request.getParameter("given_name"), 
                        request.getParameter("surname"),
                        request.getParameter("email"));
                if (success) {
                    User user = userFacade.findByUsername(request.getParameter("username"));
                    session.setAttribute("User", user);
                    response.sendRedirect("home");
                } else {
                    response.sendRedirect("register");
                }
                return;
            }
            request.getRequestDispatcher("/WEB-INF/view/unauth/register.jsp").forward(request, response);
            return;
        }
        
        /* Forgot your password use case */
        if (request.getServletPath().equals("/forgotpassword")) {
            request.getRequestDispatcher("/WEB-INF/view/unauth/forgotpassword.jsp").forward(request, response);
            return;
        }
        
        /* Demand a log in for everything else */
        if (session.getAttribute("User") == null) {
            request.getRequestDispatcher("/WEB-INF/view/unauth/login.jsp").forward(request, response);
            return;
        }
        
        /* Handle logout button */
        if (request.getServletPath().equals("/logout")) {
            session.invalidate();
            response.sendRedirect("");
            return;
        }
        
        /* Dispatch to different views */
        switch (request.getServletPath()) {
            case "/home":
                userPath = "/home";
                break;
            case "/manage":
                userPath = "/manage";
                break;
            case "/mycourses":
                userPath = "/user/courses";
                break;
            case "/lecture":
                userPath = "/lecture";
                break;
            case "/register":
                userPath = "/register";
                break;
            default:
                userPath = "/home";
                break;
                
        }
        
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view/auth" + userPath + ".jsp";

        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
