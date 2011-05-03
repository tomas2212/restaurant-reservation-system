/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.restaurant;

/**
 *
 * @author xsvrcek1
 */
public class Manager {
    
    private UserManager um;
    private TableManager tm;

    public Manager(UserManager um, TableManager tm) {
        this.um=um;
        this.tm=tm;
    }

    public Reservation newReservation(User user, Table table){
        return new Reservation(user, table);
    }
    






}
