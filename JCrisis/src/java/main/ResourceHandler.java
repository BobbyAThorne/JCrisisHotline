/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Accessors.ResourceAccessor;
import Beans.Resource;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chrsitain Lopez
 */
public class ResourceHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        //int resourceId, String categories, String name, String phone, String addressOne, String addressTwo, String city, String territory, String country, String postalCode, String email, String description
        int id;
        try {
            id = Integer.parseInt(request.getParameter("resourceId"));
        } catch (Exception e) {
            id = 0;
        }

        HttpSession session = request.getSession();

        String categoryCSV = request.getParameter("resourceCategory");
        String[] categoryArray = categoryCSV.split(",");
        try {
            for (String category : categoryArray) {
                String temp = category.trim();
                temp = temp;
                if (!(ResourceAccessor.getOccurances(temp) > 0)) {
                    // Not in the db yet. Add simple record, but not 100% accurate.
                    ResourceAccessor.createResourceCategory(category, category);
                }
            }
        } catch (Exception e) {
            response.sendRedirect("ErrorPage.html");
        }

        Resource resource = new Resource(id, request.getParameter("resourceCategory"),
                request.getParameter("resourceName"), request.getParameter("resourcePhone"),
                request.getParameter("resourceAddress1"), request.getParameter("resourceAddress2"),
                request.getParameter("resourceCity"), request.getParameter("resourceTerritory"),
                request.getParameter("resourceCountry"), request.getParameter("resourcePostalCode"),
                request.getParameter("resourceEmail"), request.getParameter("resourceDescription"));

        if (resource.isValid()) {
            ///ResourceAccessor resourceDAO = new ResourceAccessor();
            try {
                //resourceDAO.createResource(resource);
                ResourceAccessor.createResource(resource);// sets the Id to the one just made in the DB
                for (String category : categoryArray) {
                    String temp = category.trim();
                    ResourceAccessor.createResourceCategoryResource(temp, resource.getResourceId());
                }

                session.removeAttribute("resourceBean");
                request.getRequestDispatcher("Resources.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendRedirect("ErrorPage.html");
            }
        } else {
            session.setAttribute("resourceBean", resource);
            //request.getRequestDispatcher("resources/ResourceDetails.jsp").forward(request, response);
            response.sendRedirect("resources/ResourceDetails.jsp");
            //request.getRequestDispatcher(request.getContextPath() + "/resources/ResourceDetails.jsp").forward(request, response);
        }
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ResourceHandler</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ResourceHandler at " + request.getContextPath() + "</h1>");
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
