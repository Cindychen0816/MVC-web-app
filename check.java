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

public class check extends HttpServlet{
    
    private Statement st;
    Boolean valid = false;
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                
        ResultSet rs = null;
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
        String fn = request.getParameter("fn");
        String ln = request.getParameter("ln");
        String em = request.getParameter("em");
        String cn = request.getParameter("cn");
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/Xinran";
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            System.out.println("Connect successfully!");
            st = conn.createStatement();
            
            String q1 = new String("SELECT * FROM IS2560.STUDENT WHERE FIRST_NAME='"+fn+"' AND LAST_NAME='"+ln+"' AND EMAIL='"+em+"'");
            rs = st.executeQuery(q1);
            
            if(rs.next()){
                valid=true;
            }
                rs.close(); 
                st.close();
                conn.close();
        }       
        catch (SQLException se){
            se.printStackTrace();  
            System.out.println("Connect failed!");
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(check.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not found!");
        }
      
        if(valid){
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Message Confirmation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 align=\"center\">Welcome " + fn + "</h1>");
            out.println("<h3 align=\"center\"><a href =\"list\">Now, you can check the student list here.</a></h3>");
            out.println("</body>");
            out.println("</html>");
            valid=false;
        }else{
            out.print("<h2 align=\"center\"><i>Sorry the user is not exist!</i></h2>");
            RequestDispatcher rd=request.getRequestDispatcher("login");
            rd.include(request, response);
        }
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
    
}
