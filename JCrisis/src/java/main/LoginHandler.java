package main;

import Accessors.UserAccessor;
import Beans.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This Servlet handles login requests.
 *
 * @author Tim Lansing
 */
public class LoginHandler extends HttpServlet {

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

        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        String nextLocation = "Login.jsp";

        User user = null;

        // Next section creates dummy user to be used until salting and hashing are implemented.
//        user = new User(); // Added by TL need to remove when salt and hashing is complete
//        user.setID(10000);
//        user.setFirstName("Bob");
//        user.setLastName("Trapp");
//        user.setPhone("555-555-5555");
//        user.setAddressOne("1010 Java Wayfun");
//        user.setAddressTwo("");
//        user.setCity("Cedar Rapids");
//        user.setTerritory("IA");
//        user.setZip("52404");
        //user.setRoles(new ArrayList<>(Arrays.asList("")));
        //user.setRoles(new ArrayList<>(Arrays.asList("reports", "counselor", "manager", "dataEntry")));

    //Uncomment below lines when salting and hashing are implemented.  TL
        // Validate user and if successful get a user object.
        // If unsuccessful null will be returned.
        boolean result = false;
        try{
            //user = UserAccessor.validateUser(userName, password);
            PersonHandler personHandler = new PersonHandler();
            result = personHandler.isValidUser(userName, password);
            user = personHandler.getUser(userName);
            user.setRoles(UserAccessor.retrieveUserRoles(user.getID()));
        }catch(Exception ex){
            System.out.println("Can't connect to the database.");  // May want to handle this differently TL.
        }
        
        if (result == true) {
            nextLocation = "index.jsp";
            session.setAttribute("user", user);
        }else {
            session.setAttribute("currentPageMessage", "Username or password is invalid.");
        }
        request.getRequestDispatcher(nextLocation).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
