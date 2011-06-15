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

    boolean addTable(Table table);

    boolean deleteTable(Table table);

    boolean updateTable(Table table);

    Table findTableByID(int id);

    //Collection<Table> freeTables();

    //Collection<Table> reservedTables();

    Collection<Table> freeTables(String date, int time, int duration);

    Collection<Table> allTables();
}