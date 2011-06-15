/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.restaurant;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xsvrcek1
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //all managers initialization
        Manager manager = new Manager(new UserManagerImpl(), new TableManagerImpl());
        UserManager umi = new UserManagerImpl();
        TableManager tmi = new TableManagerImpl();




        //user and table creation---------------------------
        /*Table table = new Table(false);
        table.setTableId(1);
        table.setPlaces(3);
        
        User user = new User(false);
        user.setEmail("lukas@gmail.com");
        user.setFirstname("Lukas");
        user.setSurname("Sakul");
        user.setPassword("mani");*/

        //user and table adding-------------------------
        /*umi.addUser(user);
        tmi.addTable(table);*/

        //login test---------------------------------------------------
        /*boolean jj = manager.login("tomas2212@gmail.com", "muni");
        System.out.println(jj);*/

        //free tables test----------------------------------------------------------
        //System.out.println(tmi.freeTables("2011-06-16", 720, 0).size());

        //reservation-----------------------------------------------------------
        /*Reservation reservation = new Reservation();
        reservation.setDate("2011-06-16");
        reservation.setTime(720);
        reservation.setDuration(60);
        reservation.setUser(umi.findUser("tomas2212@gmail.com"));
        reservation.setTable(tmi.findTableByID(1));*/

        //reservation creating test---------------------------------------------
        //manager.createReservation(reservation);

        //free tables test----------------------------------------------------------
        //System.out.println(tmi.freeTables("2011-06-16", 721, 2).size());

        //all users reservations test----------------------------------------------------------------------
        //System.out.println(umi.allUsersReservations(umi.findUser("jakub.papcun@gmail.com")).size());

        //delete reservation test-----------------------------------------------
        //manager.deleteReservation(29);


        //deleting users---------------------------------------------
        //umi.deleteUser(umi.findUser("jakub.papcun@gmail.com"));
        
        //deletng tables-------------------------------------------------------
        //tmi.deleteTable(tmi.findTableByID(2));
    }
}