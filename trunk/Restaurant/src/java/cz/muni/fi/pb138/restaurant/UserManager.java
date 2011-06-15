/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.restaurant;

import java.util.Collection;

/**
 *
 * @author xsvrcek1
 */
public interface UserManager {
    
    boolean addUser(User user);

    boolean deleteUser(User user);

    boolean updateUser(User user);

    User findUser(String email);

    Collection<User> allUsers();

    Collection<Reservation> allUsersReservations(User user);
}