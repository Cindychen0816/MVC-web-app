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
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class list extends HttpServlet {
    
    private Connection conn;
    private Statement st;
    private ResultSet rs = null;
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/Xinran";
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            System.out.println("Connect successfully!");
            st = conn.createStatement();
            String q1 = new String("SELECT * FROM IS2560.STUDENT");
            rs =  st.executeQuery(q1);
        }        
        catch (SQLException se){
            se.printStackTrace();
            System.out.println("Connect failed!");
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(list.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not found!");
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<title>List Messages</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Here are the following Messages</h1>");
        out.println("<ol>");
        try{
            while(rs.next()){
                out.println("<li><i>Full Name:</i>"+rs.getString("FIRST_NAME")+"   "+rs.getString("LAST_NAME")+"   <br/><i>Email:</i> "+rs.getString("EMAIL")+"  <br/><i>Content:</i> "+rs.getString("CONTENT")+"</li>");
            }
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            System.out.println("SQL exception!");
        }
        out.println("</ol>");
        out.println("<h3><a href =\"index.jsp\">Back to the submit page.</a></h3>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
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
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
    
}
