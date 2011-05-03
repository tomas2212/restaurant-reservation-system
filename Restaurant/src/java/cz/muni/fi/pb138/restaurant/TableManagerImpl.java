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
public class TableManagerImpl implements TableManager{

    private boolean vip;

    Collection<Table> tables;

    public boolean addTable() {
        Table table = new Table(vip);
        return false;
    }

    public boolean deleteTable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean updateTable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<Table> freeTables() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<Table> reservedTables() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Table findTableByID(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  
}
