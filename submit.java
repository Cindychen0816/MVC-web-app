/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xinranchen
 */

import java.io.*;
import java.net.*;
import java.util.regex.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class submit extends HttpServlet{
    private Connection conn;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Boolean valid = false;
        String clean_author, clean_title, clean_message, clean_cont;
        Statement st;
        Matcher matcher;
        
        // Compile regular expression
        Pattern pattern = Pattern.compile("[;:,#&'\"!?]");
        // Replace all occurrences of pattern in input
        matcher = pattern.matcher(request.getParameter ("sender"));
        clean_author = matcher.replaceAll("|");
        matcher = pattern.matcher(request.getParameter ("title"));
        clean_title = matcher.replaceAll("|");
        matcher = pattern.matcher(request.getParameter ("message"));
        clean_message = matcher.replaceAll("|");
        matcher = pattern.matcher(request.getParameter ("cont"));
        clean_cont = matcher.replaceAll("|");
        
        //open the DBMS and insert the record
        try{
                      
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/Xinran";
            conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            System.out.println("Connect successfully!");
            st = conn.createStatement();
            
            String q1 = new String("INSERT INTO IS2560.STUDENT(FIRST_NAME, LAST_NAME, EMAIL, CONTENT) VALUES ('"+clean_author+"', '"+clean_title+"', '"+clean_message+"', '" +clean_cont + "')");
                System.out.println(q1);
                st.executeUpdate(q1);
                st.close();
                conn.close();
                valid=true;
        }        
        catch (SQLException se)
        {
            se.printStackTrace();
            System.out.println("Connect failed!");
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(submit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not found!");
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        if (valid){
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Message Confirmation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 align=\"center\">Hello " + clean_author+ "</h1>");
            out.println("<p align=\"center\">We stored the following:</p><ol>");
            out.println("<p align=\"center\"><i>First Name: </i>"+clean_author+"</p>");
            out.println("<p align=\"center\"><i>Last Name: </i>"+clean_title+"</p>");
            out.println("<p align=\"center\"><i>Email: </i>"+clean_message+"</p>");
            out.println("<p align=\"center\"><i>Content: </i>"+clean_cont+"</p>");
            out.println("</ol>");
            out.println("<h3 align=\"center\"><a href =\"login\">Now click here to log in.</a></h3>");        
            out.println("</body>");
            out.println("</html>");
        }else{
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Message Storage Problem</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 align=\"center\">Hello " + request.getParameter ("sender") + "</h1>");
            out.println("<p align=\"center\">We were unable to store the message:</p><ol><p>");
            out.println(request.getParameter("message"));
            out.println("</p></ol>");
            out.println("<h3 align=\"center\"><a href =\"login\">Please log in.</a></h3>");        
            out.println("</body>");
            out.println("</html>");   
        }
        out.close();
        
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    
}
