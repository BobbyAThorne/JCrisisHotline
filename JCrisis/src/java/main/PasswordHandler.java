/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Accessors.UserAccessor;
//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This Servlet uses Request Dispatcher on ChangePassword.jsp to process a
 * user's password.
 *
 * @author Shade
 */
public class PasswordHandler extends HttpServlet {

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

//        String oldPassword = request.getParameter("oldPassword");
//        String newPassword = request.getParameter("newPassword");
//        String userID = (String) request.getSession().getAttribute("userName");
//        int numUserID = Integer.parseInt(userID);
//        byte[] oldSalt = null;
//        try {
//            oldSalt = Base64.decode(UserAccessor.retrievePasswordSalt(numUserID));
//        } catch (SQLException | Base64DecodingException ex) {
//            //How do we deal with these? The latter should never happen...
//        }
//        byte[] newSalt = HashHelper.generateSalt();
//        String oldHash = HashHelper.hashPassword(oldPassword, oldSalt);
//        String newHash = HashHelper.hashPassword(newPassword, newSalt);
//
//        try {
//            boolean result = UserAccessor.updatePassword(oldHash, Base64.encode(oldSalt), newHash, Base64.encode(newSalt), numUserID);
//        } catch (SQLException ex) {
//
//        }
        request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
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
