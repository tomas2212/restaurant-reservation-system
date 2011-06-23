/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.restaurant;

import java.io.File;
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
        /*Table table1 = new Table(true);
        table1.setTableId(1);
        table1.setPlaces(3);

        Table table2 = new Table(false);
        table2.setTableId(2);
        table2.setPlaces(2);

        Table table3 = new Table(true);
        table3.setTableId(3);
        table3.setPlaces(4);

        User user1 = new User(false);
        user1.setEmail("jakub.papcun@gmail.com");
        user1.setFirstname("Jakub");
        user1.setSurname("Papcun");
        user1.setPassword("meni");

        User user2 = new User(false);
        user2.setEmail("tomas2212@gmail.com");
        user2.setFirstname("Tomas");
        user2.setSurname("Svrcek");
        user2.setPassword("muni");

        User user3 = new User(false);
        user3.setEmail("lukas@gmail.com");
        user3.setFirstname("Lukas");
        user3.setSurname("Sakul");
        user3.setPassword("mani");

        //user and table adding-------------------------
        umi.addUser(user1);
        tmi.addTable(table1);
        umi.addUser(user2);
        tmi.addTable(table2);
        umi.addUser(user3);
        tmi.addTable(table3);*/

        //login test---------------------------------------------------
        /*boolean jj = manager.login("tomas2212@gmail.com", "muni");
        System.out.println(jj);*/

        //System.out.println(tmi.findTableByID(1));

        //free tables test----------------------------------------------------------
        //System.out.println(tmi.freeTables("2011-06-16", 720, 0).size());

        //reservation-----------------------------------------------------------
        /*Reservation reservation1 = new Reservation();
        reservation1.setDate("2011-06-16");
        reservation1.setTime(720);
        reservation1.setDuration(60);
        reservation1.setUser(umi.findUser("tomas2212@gmail.com"));
        reservation1.setTable(tmi.findTableByID(1));

        Reservation reservation2 = new Reservation();
        reservation2.setDate("2011-06-15");
        reservation2.setTime(720);
        reservation2.setDuration(60);
        reservation2.setUser(umi.findUser("jakub.papcun@gmail.com"));
        reservation2.setTable(tmi.findTableByID(1));

        Reservation reservation3 = new Reservation();
        reservation3.setDate("2011-06-15");
        reservation3.setTime(720);
        reservation3.setDuration(60);
        reservation3.setUser(umi.findUser("lukas@gmail.com"));
        reservation3.setTable(tmi.findTableByID(2));

        Reservation reservation4 = new Reservation();
        reservation4.setDate("2011-06-16");
        reservation4.setTime(660);
        reservation4.setDuration(30);
        reservation4.setUser(umi.findUser("tomas2212@gmail.com"));
        reservation4.setTable(tmi.findTableByID(3));

        Reservation reservation5 = new Reservation();
        reservation5.setDate("2011-06-16");
        reservation5.setTime(780);
        reservation5.setDuration(60);
        reservation5.setUser(umi.findUser("jakub.papcun@gmail.com"));
        reservation5.setTable(tmi.findTableByID(1));


        //reservation creating test---------------------------------------------
        manager.createReservation(reservation1);
        manager.createReservation(reservation2);
        manager.createReservation(reservation3);
        manager.createReservation(reservation4);
        manager.createReservation(reservation5);*/

        //free tables test----------------------------------------------------------
        /*Object[] t = tmi.freeTables(umi.findUser("jakub.papcun@gmail.com"),"2011-06-16", 660, 2).toArray();
        Table tt = (Table) t[0];
        System.out.println(tt.getTableId());*/

        //System.out.println(tmi.freeTables(umi.findUser("jakub.papcun@gmail.com"),"2011-06-16", 660, 2).size());
        /*System.out.println(tmi.freeTables("2011-06-16", 779, 2).size());
        System.out.println(tmi.freeTables("2011-06-16", 781, 40).size());
        System.out.println(tmi.freeTables("2011-06-16", 840, 2).size());
        System.out.println(tmi.freeTables("2011-06-16", 839, 2).size());*/

        //all users reservations test----------------------------------------------------------------------
        //System.out.println(umi.allUsersReservations(umi.findUser("jakub.papcun@gmail.com")).size());

        //delete reservation test-----------------------------------------------
        //manager.deleteReservation(80);

        //all users
        //System.out.println(umi.allUsers().size());

        //all tables
        //System.out.println(tmi.allTables().size());

        //deleting users---------------------------------------------
        //umi.deleteUser(umi.findUser("lukas@gmail.com"));
        //umi.deleteUser(umi.findUser("jakub.papcun@gmail.com"));
        //umi.deleteUser(umi.findUser("tomas2212@gmail.com"));

        //deletng tables-------------------------------------------------------
       //tmi.deleteTable(tmi.findTableByID(3));
    }
}
