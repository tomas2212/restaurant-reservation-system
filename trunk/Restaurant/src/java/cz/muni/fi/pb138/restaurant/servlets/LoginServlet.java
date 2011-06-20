/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.restaurant.servlets;

import cz.muni.fi.pb138.restaurant.Manager;
import cz.muni.fi.pb138.restaurant.TableManager;
import cz.muni.fi.pb138.restaurant.TableManagerImpl;
import cz.muni.fi.pb138.restaurant.User;
import cz.muni.fi.pb138.restaurant.UserManager;
import cz.muni.fi.pb138.restaurant.UserManagerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Demqoo
 */
public class LoginServlet extends HttpServlet {
   //UserManagerImpl manager = new UserManagerImpl(); cez session
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         if( request.getParameter("logout") !=null && "true".equals(request.getParameter("logout"))) {
            HttpSession session = request.getSession(true);
             session.setAttribute("name", "");

        }
       request.getRequestDispatcher("/index.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         HttpSession session = request.getSession(true);
        Manager manager = (Manager)session.getAttribute("manager");
        UserManager um = manager.getUm();

        if( request.getParameter("logout") !=null && "true".equals(request.getParameter("logout"))) {
            session = request.getSession(true);
             session.setAttribute("name", "");
             session.setAttribute("email", "");

        }
        else if("true".equals(request.getParameter("login"))){
         String email = request.getParameter("email");
         String password = request.getParameter("password");
         

         if(manager.login(email, password)) {
            session = request.getSession(true);
             session.setAttribute("name", um.findUser(email).getFirstname());
             session.setAttribute("email", email);
         }
        }
        else if ("true".equals(request.getParameter("register"))) {
             session = request.getSession(true);
              String surname = request.getParameter("surname");
              String firstname = request.getParameter("firstname");
              String password = request.getParameter("password");
              String email = request.getParameter("email");
              if(surname != null && firstname != null && password != null && email!= null && !"".equals(surname) && !"".equals(firstname) && !"".equals(password) && !"".equals(email)) {
              User user = new User();
              user.setEmail(email);
              user.setFirstname(firstname);
              user.setPassword(password);
              user.setVip(false);

              if(um.addUser(user)) {
                  
                  session.setAttribute("name", user.getFirstname());
                  session.setAttribute("email", user.getEmail());
              }else {
                request.setAttribute("error", "Registration failed");
                request.getRequestDispatcher("/Registration.jsp").forward(request, response);
                return;
              }
              } else {
                  request.setAttribute("error", "One of the field was empty");
                  request.getRequestDispatcher("/Registration.jsp").forward(request, response);
                  return;
              }

         }
         request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
