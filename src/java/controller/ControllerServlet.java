package controller;

import com.sun.xml.wss.impl.c14n.CanonicalizerFactory;
import entity.Competency;
import entity.Course;
import entity.DLEGroup;
import entity.Document;
import entity.Lecture;
import entity.User;
import entity.UserCompetency;
import entity.UserCompetencyPK;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CompetencyFacade;
import session.CourseFacade;
import session.DLEGroupFacade;
import session.DocumentFacade;
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
            "/user/courses/modify/*",
            "/user/manage",
            "/user/modify/*",
            "/group/manage",
            "/group/modify/*",
            "/competency/manage",
            "/competency/modify/*",
            "/course/manage",
            "/course/modify/*",
            "/course/chat",
            "/register",
            "/forgotpassword",
            "/logout",
            "/document/*",
            "/landing",})
public class ControllerServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private CourseFacade courseFacade;
    @EJB
    private UserCompetencyFacade userCompetencyFacade;
    @EJB
    private DLEGroupFacade groupFacade;
    @EJB
    private CompetencyFacade competencyFacade;
    @EJB
    private DocumentFacade documentFacade;

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

        User user = userFacade.find(((User) session.getAttribute("User")).getId());

        // Handle the submit button
        
        try {
            if (request.getMethod().equals("POST") && request.getParameter("add") != null) {
                userCompetencyFacade.addUserCompetency(
                        user.getId(), 
                        Long.parseLong(request.getParameter("competency")), 
                        Short.parseShort(request.getParameter("skill_level")));
            } else if (request.getMethod().equals("POST") && request.getParameter("delete") != null) {
                long userId = user.getId();
                long competencyId = Long.parseLong(request.getParameter("competency"));
                UserCompetencyPK pk = new UserCompetencyPK(userId, competencyId);
                UserCompetency userCompetency = userCompetencyFacade.find(pk);
                userCompetencyFacade.remove(userCompetency);
            } 
        } catch (java.lang.NumberFormatException e) {}
        
        // Pre-fill the competency list
        request.setAttribute("competencies", user.getUserCompetencyCollection());
        request.setAttribute("all_competencies", competencyFacade.findAll());

        // Show the profile view
        request.getRequestDispatcher("/WEB-INF/view/auth/user/competencies.jsp").forward(request, response);
    }
    
    
    protected void handleUserCompetenciesModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String[] split = request.getPathInfo().split("[/-]");
        Long userId = Long.parseLong(split[1]);
        Long competencyId = Long.parseLong(split[2]);
        UserCompetencyPK pk = new UserCompetencyPK(userId, competencyId);
        HttpSession session = request.getSession();
        UserCompetency userCompetency = userCompetencyFacade.find(pk);
        Map<String, String> messages = new HashMap<>();
        
        request.setAttribute("usercompetency", userCompetency);
        if (request.getMethod().equals("POST") && request.getParameter("modify") != null) {
            try {
                short skill_level = Short.parseShort(request.getParameter("skill_level"));
                boolean success = userCompetencyFacade.editUserCompetency(userId,
                                                                          competencyId,
                                                                          skill_level);
                if (!success){
                    messages.put("error", "Databasefout!");
                }
            } catch (java.lang.NumberFormatException e) { }
            pk = new UserCompetencyPK(userId, competencyId);
            userCompetency = userCompetencyFacade.find(pk);
            request.setAttribute("usercompetency", userCompetency);   
        }
        request.getRequestDispatcher("/WEB-INF/view/subviews/user/competencies/modify.jsp").forward(request, response);
    }

    
    protected void handleUserCourses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}
        
        User user = userFacade.find(((User)session.getAttribute("User")).getId());
        
        // Handle the submit button
        if (request.getMethod().equals("POST") && request.getParameter("addUserCourse") != null) {
            Course userCourse = courseFacade.find(Long.parseLong(request.getParameter("course")));
            
            boolean success = userFacade.addUserCourse(user, userCourse);
            if (!success) {
                messages.put("error", "Databasefout!");
            }
        } else if (request.getMethod().equals("POST") && request.getParameter("delete") != null) {
            Course userCourse = courseFacade.find(Long.parseLong(request.getParameter("course")));
            boolean success = userFacade.removeUserCourse(user, userCourse);
            if (!success){
                messages.put("error", "Databasefout!");
            }
        }

        user = userFacade.find(((User)session.getAttribute("User")).getId());
        
        // Pre-fill the courses lists
        request.setAttribute("courses", user.getCourseCollection());
        request.setAttribute("all_courses", courseFacade.findAll());
        
        // Show the profile view
        request.getRequestDispatcher("/WEB-INF/view/auth/user/courses.jsp").forward(request, response);
    }

    protected void handleUserCoursesModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] split = request.getPathInfo().split("[/-]");
        Long competencyId = Long.parseLong(split[1]);
        Course course = courseFacade.find(competencyId);

        if(request.getMethod().equals("POST")) {
                long group_id = Long.parseLong(request.getParameter("group_id"));
                DLEGroup group = groupFacade.find(group_id);
                course.setManagingGroup(group);
                String name = request.getParameter("name");
                course.setName(name);
                courseFacade.edit(course);
        }

        request.setAttribute("course", course);
        request.setAttribute("lectures", course.getLectureCollection());
        request.setAttribute("documents", course.getDocumentCollection());
        
        request.getRequestDispatcher("/WEB-INF/view/subviews/user/courses/modify.jsp").forward(request, response);
    }

    
    protected void handleAgenda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}

        Collection<Lecture> lectures = userFacade.findUpcomingLectures(((User) session.getAttribute("User")).getId());

        request.setAttribute("lectures", lectures);

        // Show the user agenda view
        request.getRequestDispatcher("/WEB-INF/view/auth/user/agenda.jsp").forward(request, response);

    }

    protected void handleUserModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] split = request.getPathInfo().split("[/-]");
        Long userId = Long.parseLong(split[1]);
        User user = userFacade.find(userId);

        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/view/subviews/user/modify.jsp").forward(request, response);
    }

    protected void handleGroupModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] split = request.getPathInfo().split("[/-]");
        Long groupId = Long.parseLong(split[1]);
        DLEGroup group = groupFacade.find(groupId);

        request.setAttribute("group", group);

        request.getRequestDispatcher("/WEB-INF/view/subviews/group/modify.jsp").forward(request, response);
    }

    protected void handleCompetencyModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] split = request.getPathInfo().split("[/-]");
        Long competencyId = Long.parseLong(split[1]);
        Competency competency = competencyFacade.find(competencyId);

     
        
        request.setAttribute("competency", competency);

        request.getRequestDispatcher("/WEB-INF/view/subviews/competency/modify.jsp").forward(request, response);
    }

    protected void handleCourseModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] split = request.getPathInfo().split("[/-]");
        Long competencyId = Long.parseLong(split[1]);
        Course course = courseFacade.find(competencyId);

        if(request.getMethod().equals("POST") && request.getParameter("addCourse") != null) {
            HttpSession session = request.getSession();
            User user = userFacade.find(((User)session.getAttribute("User")).getId());
            long group_id = Long.parseLong(request.getParameter("group_id"));
            DLEGroup group = groupFacade.find(group_id);
            course.setManagingGroup(group);
            String name = request.getParameter("name");
            course.setName(name);
            course.setCreator(user);
            course.setLastModified(new Date());
            courseFacade.create(course);
        } else if(request.getMethod().equals("POST")) {
            long group_id = Long.parseLong(request.getParameter("group_id"));
            DLEGroup group = groupFacade.find(group_id);
            course.setManagingGroup(group);
            String name = request.getParameter("name");
            course.setName(name);
            courseFacade.edit(course);
        }
        

        request.setAttribute("course", course);
        request.setAttribute("groups", groupFacade.findAll());
        request.setAttribute("lectures", course.getLectureCollection());
        request.setAttribute("documents", course.getDocumentCollection());
        request.getRequestDispatcher("/WEB-INF/view/subviews/course/modify.jsp").forward(request, response);
    }

    protected void handleCompetency(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getMethod().equals("POST") && request.getParameter("modify") != null) {
            Long competencyId = Long.parseLong(request.getParameter("competency"));
            Competency competency = competencyFacade.find(competencyId);

            String name = request.getParameter("name");
            competency.setName(name);
            competencyFacade.edit(competency);
        } else if(request.getMethod().equals("POST") && request.getParameter("beheren_competency_add") != null) {
            String competencyName = request.getParameter("beheren_competency");
            Competency newCompetency = new Competency();
            newCompetency.setName(competencyName);
            competencyFacade.create(newCompetency);
        } else if(request.getMethod().equals("POST") && request.getParameter("competency_remove") != null) {
            Competency competency = competencyFacade.find(Long.parseLong(request.getParameter("beheren_competency_remove")));
            competencyFacade.remove(competency);
        }
        
        request.setAttribute("competencies", competencyFacade.findAll());

        request.getRequestDispatcher("/WEB-INF/view/auth/competency/manage.jsp").forward(request, response);
    }
    
    
    protected void handleChat(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages); // Now it's available by ${messages}
        
        // Show the chat window
        request.getRequestDispatcher("/WEB-INF/view/subviews/course/chat.jsp").forward(request, response);

    }
    
    protected void handleUserManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getMethod().equals("POST") && request.getParameter("beheer_user_remove_submit") != null) {
            User user = userFacade.find(Long.parseLong(request.getParameter("beheer_user_remove")));
            userFacade.remove(user);
        }
        
        
        request.setAttribute("users", userFacade.findAll());
        // Show the manage users window
        request.getRequestDispatcher("/WEB-INF/view/auth/user/manage.jsp").forward(request, response);

    }
   
    protected void handleDocument(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] split = request.getPathInfo().split("[/-]");
        Integer documentId = Integer.parseInt(split[1]);
       
        FacesContext fc = FacesContext.getCurrentInstance();
        Document document = documentFacade.find(documentId);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getName() + "\"");
        response.setContentType(getServletContext().getMimeType(document.getName()));
        
        OutputStream output = response.getOutputStream();
        output.write(document.getContent());
        fc.responseComplete();
        
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
        
        // Default landing page instead of empty iframe
        if (request.getServletPath().equals("/landing")) {
            request.getRequestDispatcher("/WEB-INF/view/subviews/landing.jsp").forward(request, response);
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
            case "/user/courses/modify":
                handleUserCoursesModify(request, response);
                return;
            case "/user/profile":
                handleProfile(request, response);
                return;
            case "/user/agenda":
                handleAgenda(request, response);
                return;
            case "/course/chat":
                handleChat(request, response);
                return;
            case "/user/manage":
                handleUserManage(request, response);
                return;
            case "/user/modify":
                handleUserModify(request, response);
                return;
            case "/group/manage":
                request.setAttribute("groups", groupFacade.findAll());
                viewTemplate = "group/manage.jsp";
                break;
            case "/group/modify":
                handleGroupModify(request, response);
                return;
            case "/competency/manage":
                handleCompetency(request, response);
                return;
            case "/competency/modify":
                handleCompetencyModify(request, response);
                return;
            case "/course/manage":
                request.setAttribute("courses", courseFacade.findAll());
                viewTemplate = "course/manage.jsp";
                break;
            case "/course/modify":
                handleCourseModify(request, response);
                return;
            case "/document":
                handleDocument(request, response);
                return;
            /* Standard template views below */
            case "/home":
                viewTemplate = "home.jsp";
                break;
            case "/manage":
                viewTemplate = "manage.jsp";
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
