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
public interface TableManager {

    boolean addTable();

    boolean deleteTable();

    boolean updateTable();

    Table findTableByID(int id);

    Collection<Table> freeTables();

    Collection<Table> reservedTables();

}
