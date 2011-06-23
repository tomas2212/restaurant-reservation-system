/*
 * Servlet handling booking table displaying
 * 
 */
package cz.muni.fi.pb138.restaurant.servlets;

import cz.muni.fi.pb138.restaurant.Manager;
import cz.muni.fi.pb138.restaurant.Reservation;
import cz.muni.fi.pb138.restaurant.Table;
import cz.muni.fi.pb138.restaurant.TableManager;
import cz.muni.fi.pb138.restaurant.User;
import cz.muni.fi.pb138.restaurant.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Robert Zahradnicek
 */
public class ReservationServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("logout") != null && "true".equals(request.getParameter("logout"))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("name", "");
            session.invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession(false);

        Manager manager = new Manager();
        UserManager um = manager.getUm();
        TableManager tm = manager.getTm();

        if (session == null) {
            request.getRequestDispatcher("/Registration.jsp").forward(request, response);
        }

         if (request.getParameter("month") != null && request.getParameter("day") != null && !"".equals(request.getParameter("month")) &&!"".equals(request.getParameter("day"))  ) {
             int month = Integer.parseInt(request.getParameter("month")) ;
             int day = Integer.parseInt(request.getParameter("day")) ;
             if(checkDate(day,month)) {
              request.setAttribute("date", request.getParameter("day") + "." + request.getParameter("month") );
              request.getRequestDispatcher("/Reservation.jsp").forward(request, response); return;
             } else {
                 request.setAttribute("error", "Date was wrong");
                request.getRequestDispatcher("/Reservation.jsp").forward(request, response);
                return;
             }

         }
         String book = request.getParameter("book");
        if ("true".equals(request.getParameter("book"))) {


            String name =(String) session.getAttribute("name");
            String surname =(String) session.getAttribute("surname");
            String email = (String) session.getAttribute("email");
            String date = request.getParameter("date");

            Reservation reservation;

            if (surname != null && name != null && email != null && !"".equals(surname) && !"".equals(name) && !"".equals(email)) {
                Table table = tm.findTableByID(Integer.parseInt(request.getParameter("table")));
                User user = um.findUser(email);

                if ((table.isVip() && !user.isVip())) {
                
                    request.setAttribute("error", "You are not a VIP please select non-VIP table");
                    request.getRequestDispatcher("/Reservation.jsp").forward(request, response);
                    return;
                }

                int time = Integer.parseInt(request.getParameter("time_hour")) * 60 + Integer.parseInt(request.getParameter("time_minute"));
                int duration = 60;

                reservation = new Reservation(user, table);

                //reservation.setDate(date);
                reservation.setTime(time);
                reservation.setDate(date);
                reservation.setDuration(duration);

                manager.createReservation(reservation);
                manager.createUsersReservation(reservation, user);

                request.setAttribute("success", "Reservation Successfull");
                request.setAttribute("reservation", reservation);
            } else {
                request.setAttribute("error", "You are not joined");
                request.getRequestDispatcher("/Reservation.jsp").forward(request, response);
                return;
            }
        }

        /*if ("true".equals(request.getParameter("login"))
        && ("false".equals(request.getParameter("book")))) {
        
        String name = (String) session.getAttribute("name");
        String surname = (String) session.getAttribute("surname");
        String email = (String) session.getAttribute("email");
        }*/
        //request.setAttribute("error", "Not Logged in");
        request.getRequestDispatcher("/Reservation.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean checkDate(int day, int month) {
        if (month == 1 && day >31) {return false;}
        if (month == 2 && day >28) {return false;}
        if (month == 3 && day >31) {return false;}
        if (month == 4 && day >30) {return false;}
        if (month == 5 && day >31) {return false;}
        if (month == 6 && day >30) {return false;}
        if (month == 7 && day >31) {return false;}
        if (month == 8 && day >31) {return false;}
        if (month == 9 && day >30) {return false;}
        if (month == 10 && day >31) {return false;}
        if (month == 11 && day >30) {return false;}
        if (month == 12 && day >31) {return false;}

        return true;
    }
}