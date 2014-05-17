package controller;

import entity.Lecture;
import entity.User;
import entity.UserCompetency;
import entity.UserCompetencyPK;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CourseFacade;
import session.UserCompetencyFacade;
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
            "/user/agenda",
            "/user/profile",
            "/user/competencies",
            "/user/competencies/modify/*",
            "/user/courses",
            "/user/list",
            "/group/list",
            "/course/list",
            "/competency/list",
            "/course/lecture/view",
            "/course/lecture/manage",
            "/register",
            "/forgotpassword",
            "/logout",
            "/courses/([0-9]+)/documents",
            "/courses/([0-9]+)/lectures",})
public class ControllerServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private CourseFacade courseFacade;
    
    @EJB
    private UserCompetencyFacade userCompetencyFacade;
    
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
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}

        HttpSession session = request.getSession();
        User user = userFacade.findByUsername(request.getParameter("username"));
        if (user == null || !user.isPasswordCorrect(request.getParameter("password"))) {
            /* Fail! */
            messages.put("error", "Gebruikersnaam/wachtwoord onjuist");

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
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}

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
                messages.put("error", "Fout bij toevoegen.");
                request.getRequestDispatcher("/WEB-INF/view/unauth/register.jsp").forward(request, response);
            }
            return;
        }

        // Show the register form
        request.getRequestDispatcher("/WEB-INF/view/unauth/register.jsp").forward(request, response);
    }

    protected void handleProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}

        // Special case for here. This data comes from the login session.
        // For other views use objectFacade.find(id)
        User user = (User) session.getAttribute("User");

        if (request.getMethod().equals("POST")) {
            user.setGivenName(request.getParameter("given_name"));
            user.setSurname(request.getParameter("surname"));
            user.setEmail(request.getParameter("email"));

            // Password change handling, very specific to this handler.
            String password = null;
            if (!request.getParameter("password").equals(request.getParameter("confirm_password"))) {
                messages.put("error", "Wachtwoorden zijn niet gelijk!");
            } else {
                if (request.getParameter("password").equals("")) {
                    user.setPassword(request.getParameter("password"));
                } else {
                    password = request.getParameter("password");
                }
            }


            // Call the database backend to write the user entry.
            boolean success = userFacade.modifyUser(
                    user.getId(),
                    user.getGivenName(),
                    user.getSurname(),
                    user.getEmail(),
                    password);

            if (!success) {
                messages.put("error", "Databasefout!");
            }

        }

        // Pre-fill the form fields
        request.setAttribute("given_name", user.getGivenName());
        request.setAttribute("surname", user.getSurname());
        request.setAttribute("email", user.getEmail());
        
        // Show the profile view
        request.getRequestDispatcher("/WEB-INF/view/auth/user/profile.jsp").forward(request, response);
    }

    protected void handleUserCompetencies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}
     
        User user = userFacade.find(((User)session.getAttribute("User")).getId());

        // Pre-fill the competency list
        request.setAttribute("competencies", user.getUserCompetencyCollection());
        
        // Show the profile view
        request.getRequestDispatcher("/WEB-INF/view/auth/user/competencies.jsp").forward(request, response);
    }
    
    protected void handleUserCompetenciesModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] split = request.getPathInfo().split("[/-]");
        Long userId = Long.parseLong(split[1]);
        Long competencyId = Long.parseLong(split[2]);
        UserCompetencyPK pk = new UserCompetencyPK(userId, competencyId);
        UserCompetency userCompetency = userCompetencyFacade.find(pk);

        request.setAttribute("usercompetency", userCompetency);

        request.getRequestDispatcher("/WEB-INF/view/subviews/user/competencies/modify.jsp").forward(request, response);
    }

    protected void handleUserCourses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}
        
        User user = userFacade.find(((User)session.getAttribute("User")).getId());
        
        // FIXME: Remove this later. This is the example for how to use findAll()
        //request.setAttribute("all_courses", courseFacade.findAll());
        
        // Pre-fill the courses list
        request.setAttribute("courses", user.getCourseCollection());

        // Show the profile view
        request.getRequestDispatcher("/WEB-INF/view/auth/user/courses.jsp").forward(request, response);
    }
    
    
    protected void handleAgenda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}
        
        Collection<Lecture> lectures = userFacade.findUpcomingLectures(((User)session.getAttribute("User")).getId());
        
        request.setAttribute("lectures", lectures);
        
        // Show the user agenda view
        request.getRequestDispatcher("/WEB-INF/view/auth/user/agenda.jsp").forward(request, response);
        
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
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}                

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
            if (!request.getServletPath().equals("")) {
                messages.put("error", "Sessie verlopen");
            }
            request.getRequestDispatcher("/WEB-INF/view/unauth/login.jsp").forward(request, response);
            return;
        }

        // *********
        // Below here requires login!
        // *********
        
        // Dispatch to different views
        String viewTemplate;

        switch (request.getServletPath()) {
            case "/logout":
                session.invalidate();
                response.sendRedirect("");
                return;
            case "/user/competencies":
                handleUserCompetencies(request, response);
                return;
            case "/user/competencies/modify":
                handleUserCompetenciesModify(request, response);
                return;
            case "/user/courses":
                handleUserCourses(request, response);
                return;
            case "/user/profile":
                handleProfile(request, response);
                return;
            case "/user/agenda":
                handleAgenda(request, response);
                return;
            /* Standard template views below */
            case "/home":
                viewTemplate = "home.jsp";
                break;
            case "/manage":
                viewTemplate = "manage.jsp";
                break;
            case "/course/list":
                viewTemplate = "course/manage.jsp";
                break;
            case "/competency/list":
                viewTemplate = "course/manage.jsp";
                break;
            case "/user/list":
                viewTemplate = "user/manage.jsp";
                break;
            case "/group/list":
                viewTemplate = "group/manage.jsp";
                break;
            case "/register":
                viewTemplate = "register.jsp";
                break;

            // FIXME: Course needs to have an Id inserted
            case "/course/lecture":
                viewTemplate = "course/lecture/manage.jsp";
                break;
            case "/course/lecture/view":
                viewTemplate = "course/lecture/view.jsp";
                break;
            case "/course/document":
                viewTemplate = "course/document/manage.jsp";
                break;
            default:
                viewTemplate = "home.jsp";
                break;

        }

        // use RequestDispatcher to forward request internally
        request.getRequestDispatcher("/WEB-INF/view/auth/" + viewTemplate).forward(request, response);
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
