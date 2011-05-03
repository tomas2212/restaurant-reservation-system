/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pb138.restaurant;

/**
 *
 * @author xsvrcek1
 */
public interface UserManager {
    
    boolean addUser();

    boolean deleteUser();

    boolean updateUser();

    User findUser(String email);

    
}
