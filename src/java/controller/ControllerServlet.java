package controller;

import entity.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
     * Handles the login submission.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}


        HttpSession session = request.getSession();
        User user = userFacade.findByUsername(request.getParameter("username"));
        if (user == null || !user.isPasswordCorrect(request.getParameter("password"))) {
            /* Fail! */
            messages.put("error", "Gebruikersnaam en/of wachtwoord onjuist.");
            
            request.getRequestDispatcher("/WEB-INF/view/unauth/login.jsp").forward(request, response);
            return;
        }
        session.setAttribute("User", user);
        response.sendRedirect("home");
 
    }
    /**
     * Handles the register new user view
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Handle a register form submission.
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
        
            // Show the register form
            request.getRequestDispatcher("/WEB-INF/view/unauth/register.jsp").forward(request, response);
    }
    
    
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
                
        
        // Handle logging in
        if (request.getServletPath().equals("/login")) {
            handleLogin(request, response);
            return;
        }
        
        // Handle register and register view.
        if (request.getServletPath().equals("/register")) {
            handleRegister(request, response);
            return;
        }
        
        // Forgot your password use case
        if (request.getServletPath().equals("/forgotpassword")) {
            request.getRequestDispatcher("/WEB-INF/view/unauth/forgotpassword.jsp").forward(request, response);
            return;
        }
        
        // Demand a log in for everything else
        if (session.getAttribute("User") == null) {
            request.getRequestDispatcher("/WEB-INF/view/unauth/login.jsp").forward(request, response);
            return;
        }
        
        // Handle logout button
        if (request.getServletPath().equals("/logout")) {
            session.invalidate();
            response.sendRedirect("");
            return;
        }

        // Dispatch to different views
        String viewTemplate;

        switch (request.getServletPath()) {
            case "/home":
                viewTemplate = "/home.jsp";
                break;
            case "/user/courses":
                viewTemplate = "/user/courses.jsp";
                break;
            case "/manage":
                viewTemplate = "/manage.jsp";
                break;
            case "/course/list":
                viewTemplate = "/course/manage.jsp";
                break;
            case "/competency/list":
                viewTemplate = "/course/manage.jsp";
                break;
            case "/user/list":
                viewTemplate = "/user/manage.jsp";
                break;
            case "/group/list":
                viewTemplate = "/group/manage.jsp";
                break;
            case "/register":
                viewTemplate = "/register.jsp";
                break;
                
            // FIXME: Course needs to have an Id inserted
            case "/course/lecture":
                viewTemplate = "/course/lecture/manage.jsp";
                break;
            case "/course/lecture/view":
                viewTemplate = "/course/lecture/view.jsp";
                break;
            case "/course/document":
                viewTemplate = "/course/document/manage.jsp";
                break;
            default:
                viewTemplate = "/home.jsp";
                break;
                
        }
        
        // use RequestDispatcher to forward request internally
        request.getRequestDispatcher("/WEB-INF/view/auth" + viewTemplate).forward(request, response);
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
