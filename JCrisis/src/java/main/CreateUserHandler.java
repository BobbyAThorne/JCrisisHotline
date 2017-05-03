/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Accessors.UserAccessor;
import static Accessors.UserAccessor.createUser;
import Beans.User;
import Beans.UserPageBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NH228U23
 */
public class CreateUserHandler extends HttpServlet {

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
        UserPageBean pageBean = new UserPageBean();
        String nextLocation = "Users";
        if(null!=request.getParameter("submit")){
            
            // Do stuff
            try {

                User newUser = new User();
                newUser.setFirstName(request.getParameter("firstName"));
                newUser.setLastName(request.getParameter("lastName"));
                newUser.setUserName(request.getParameter("UserName"));
                newUser.setPhone(request.getParameter("phone"));
                newUser.setAddressOne(request.getParameter("addressOne"));
                newUser.setAddressTwo(request.getParameter("addressTwo"));
                newUser.setCity(request.getParameter("city"));
                newUser.setTerritory(request.getParameter("territory"));
                newUser.setZip(request.getParameter("zip"));
                newUser.validate();
                if(!newUser.isValid()) {
                    pageBean.setErrorMessage("Invaild Input");
                    session.setAttribute("userBean", pageBean);
                    request.getRequestDispatcher("/users/CreateUser.jsp").forward(request, response);
                    return;
                }
                if (createUser(newUser)) {
                    response.sendRedirect(nextLocation);
                    return;
                }

            } catch (SQLException ex) {
                pageBean.setErrorMessage("Internal error: " + ex.getMessage());
                session.setAttribute("userBean", pageBean);
               request.getRequestDispatcher("/users/CreateUser.jsp").forward(request, response); 
            }
        }
        
        request.getRequestDispatcher("/users/CreateUser.jsp").forward(request, response);
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
