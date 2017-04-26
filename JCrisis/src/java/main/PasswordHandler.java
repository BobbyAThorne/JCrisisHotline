/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Accessors.UserAccessor;
import Beans.ChangePasswordBean;
import Beans.User;
//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.IOException;
import java.sql.SQLException;
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

        ChangePasswordBean bean = new ChangePasswordBean();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String message = "";
        User user = (User) request.getSession().getAttribute("user");
        String userName = user.getUserName();
        int userId = user.getID();
        boolean oldPasswordMatches = false;
        String databaseHash = "";
        try {
            String oldSalt = UserAccessor.retrievePasswordSalt(userName);
            String oldHash = HashHelper.hashPassword(oldPassword, oldSalt);
            databaseHash = UserAccessor.retrievePasswordHash(userName);
            oldPasswordMatches = oldHash.equals(databaseHash);
        } catch (SQLException ex) {
            message = "Database Error in checking old password." + "\n" + ex.getMessage();
        }
        if (oldPasswordMatches) {
            String newSalt = HashHelper.generateStringSalt();
            String newHash = HashHelper.hashPassword(newPassword, newSalt);

            try {
                boolean result = UserAccessor.updatePassword(newHash, newSalt, userId);
                if (result) {
                    message = "Password updated successfully.";
                } else {
                    message = "Password failed to update but no errors were thrown... Contact your systems administrator";
                }
            } catch (SQLException ex) {
                message = "Password failed to update do to database error." + "\n" + ex.getMessage();
            }

        }
        bean.setMessage(message);
        request.setAttribute("bean", bean);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
