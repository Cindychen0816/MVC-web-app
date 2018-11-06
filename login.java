/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xinranchen
 */
import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class login extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
    
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 align=\"center\">Login Page</h1>");
        out.println("<div style=\"width:100%;text-align:center\">");
        out.println("<form method =\"post\" action = \"check\">");
        out.println("<p>First Name: </p>");
        out.println("<input type=\"text\" name=\"fn\" value=\"\" size=\"50\" />");
        out.println("<br />");
        out.println("<p>Last Name: </p>");
        out.println("<input type=\"text\" name=\"ln\" value=\"\" size=\"50\" />");
        out.println("<br />");
        out.println("<p>Email: </p>");
        out.println("<input type=\"text\" name=\"em\" value=\"\" size=\"50\" />");
        out.println("<br />");
        out.println("<p>Content: </p>");
        out.println("<input type=\"text\" name=\"cn\" value=\"\" size=\"50\" />");
        out.println("<br />");
        out.println("<br />");
        out.println("<input type=\"submit\" value=\"Log in\" name=\"Submit\" />");
        out.println("</form> ");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
          
        processRequest(request, response);
        String fn = request.getParameter("fn");
        String ln = request.getParameter("ln");
        String em = request.getParameter("em");
        String cn = request.getParameter("cn");
        HttpSession session = request.getSession();
        session.setAttribute("fn", fn);
        session.setAttribute("ln", ln);
        session.setAttribute("em", em);
        session.setAttribute("cn", cn);

        
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
}
