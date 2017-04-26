/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Accessors.CallerAccessor;
import Beans.CallRecord;
import Beans.Caller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eric Walton
 */
@WebServlet(name = "CallRecordHandler", urlPatterns = {"/CallRecordHandler"})
public class CallRecordHandler extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        
        
        
        String counselorID = request.getParameter("counselorID");
        String dateTime = request.getParameter("dateTime");
        String firstName = request.getParameter("firstName");
        String city = request.getParameter("city");
        String lastName = request.getParameter("lastName");
        String state = request.getParameter("state");
        String phone = request.getParameter("phone");
        String zip = request.getParameter("zip");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String callType = request.getParameter("callType");

        Caller _caller = new Caller();
        _caller.setFirstName(firstName);
        _caller.setLastName(lastName);
        _caller.setPhone(phone);
        _caller.setCity(city);
        _caller.setState(state);
        _caller.setZip(zip);
        _caller.setAddress(address);
        CallRecord _callRecord = new CallRecord();

        if (request.getParameter("btnCreate") != null) {
            // handle create stuff here!
            try {
                if (CallerAccessor.createCallRecord(_caller, _callRecord)) {
                   
                }else{
                    
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            request.getRequestDispatcher("CallRecord.jsp").forward(request, response);
        } else if (request.getParameter("btnUpdate") != null) {
            // handle update stuff here.
            request.getRequestDispatcher("CallRecord.jsp").forward(request, response);
        }

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("Servlet CallRecordHandler " + callType);            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CallRecordHandler at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
