<%-- 
    Document   : CallsReport
    Created on : Apr 25, 2017, 12:39:20 PM
    Author     : Toppy
--%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.jasperreports.view.JasperViewer"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="java.io.FileOutputStream"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>


<%@page import="Accessors.Connector"%>
<%@page import ="net.sf.jasperreports.engine.*"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>

<%
    Connection conn = null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JCrisis_Hotline_DB?useSSL=false&user=JCrisisServer&password=apple");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JCrisis_Hotline_DB","root","password");
       
        // String jrxmlFile = session.getServletContext().getRealPath("reports/CallsReport.jasper");
        File file = new File (application.getRealPath("reports/CallsReport.jasper"));
         Map parameter = new HashMap();
         parameter.put(null, null);
         byte[] bytes = JasperRunManager.runReportToPdf(file.getPath(), parameter, conn);
         response.setContentType("application/pdf");
         response.setContentLength(bytes.length);
         ServletOutputStream output = response.getOutputStream();
         output.write(bytes,0,bytes.length);
         output.flush();
         output.close();
         
         
         
       
       // InputStream input = new FileInputStream(new File(jrxmlFile));
        //JasperReport jasperReport = JasperCompileManager.compileReport(input);
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, conn);  
       
        //JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        //byte[] report  = JasperExportManager.exportReportToPdf(jasperPrint);
        
       

        //response.getOutputStream().flush();
        //response.getOutputStream().close();
        /*File reportFile = new File(application.getRealPath("//reports//CallReport.jasper"));
        
        byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),null,conn);
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.flush();
        outStream.close();
          */
        
        
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    finally
    {
        if(conn!=null)
        {
            conn.close();
        }
    }
%>
