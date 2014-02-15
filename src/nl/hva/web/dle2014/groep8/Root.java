package nl.hva.web.dle2014.groep8;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/")
public class Root extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * Initializes the root.
     */
    public Root() {
        super(); // Important to call the HttpServlet constructor
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This function redirects to the Main.jsp server page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(javax.servlet.http.HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location", "Main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// We don't allow post requests here.
		response.setStatus(javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

}
