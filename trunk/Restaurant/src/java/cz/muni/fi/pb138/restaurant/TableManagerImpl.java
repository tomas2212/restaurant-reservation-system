/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class which contains table manager methods
 *
 * @author Jakub Papcun, Tomas Svrcek & team
 */
public class TableManagerImpl implements TableManager {

    //private Table table;
    //private Collection<Table> tables;

    /**
     * Create directory
     *
     * @param table a table
     * @return true if directory was successful created or false if not
     */
    public boolean createDirectory(Table table) {

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/TABLES/" + table.getTableId());

        boolean success = false;
        try {
            if (file.exists()) {
                System.out.println("Directory with this name already exists !");
                return false;
            } else {
                success = file.mkdir();
                if (success) {
                    System.out.println("Directory: " + table.getTableId() + " was created");
                    return true;
                } else {
                    System.out.println("Error when trying to create the directory" + table.getTableId());
                    return false;
                }
            }
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error in creating directory: " + e.getMessage());
        } finally {
            if (success) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Delete from directory from TABLES/
     *
     * @param table a table
     * @return true if directory was deleted or false if not
     */
    public boolean deleteDirectory(Table table) {

        boolean success = false;

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/TABLES/" + table.getTableId());

        try {
            if (file.exists()) {
                success = file.delete();
                System.out.println("Directory " + table.getTableId() + " was removed !");
                return success;
            } else {
                System.out.println("Directory with this name doesn't exists !");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (success) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Add table to directory TABLES
     *
     * @param table a table
     * @return true if table was added or false if not
     */
    public boolean addTable(Table table) {
        return (createDirectory(table) && createXmlFile(table));
    }

    /**
     * Delete table from directory TABLES/
     *
     * @param table
     * @return true if table was deleted or false if not
     */
    public boolean deleteTable(Table table) {
        return (deleteXmlFile(table) && deleteDirectory(table));
    }

    /**
     * Delete xml file and create xml file(not directory)
     *
     * @param table
     * @return true if delete and create was successful or false if not
     */
    public boolean updateTable(Table table) {
        return (deleteTable(table) && addTable(table));
    }

    /**
     * Find table by ID and return table
     *
     * @param id  table id
     * @return table if exists or null if doesn't
     */
    public Table findTableByID(int id) {

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/TABLES/" + id);

        if (file.exists()) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = null;

                try {
                    doc = docBuilder.parse(new File(file.getAbsolutePath() + "/table.xml"));
                } catch (SAXException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                Element tableElement = doc.getDocumentElement();

                NodeList table_ids = tableElement.getElementsByTagName("table_id");
                Element tab = (Element) table_ids.item(0);
                String tableIdStr = tab.getTextContent();
                int table_id = Integer.parseInt(tableIdStr);

                NodeList placesN = tableElement.getElementsByTagName("places");
                Element pla = (Element) placesN.item(0);
                String placesStr = pla.getTextContent();
                int places = Integer.parseInt(placesStr);


                NodeList vips = tableElement.getElementsByTagName("vip");
                Element v = (Element) vips.item(0);
                String vipStr = v.getTextContent();
                boolean vip = Boolean.parseBoolean(vipStr);

                Table newTable = new Table(vip);
                newTable.setTableId(table_id);
                newTable.setPlaces(places);

                return newTable;
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            System.out.println("Table with this id doesn't exist.");
            return null;
        }
    }

    /**
     * Create XML file with param table
     *
     * @param table a table
     * @return true if XML document was created or false if not
     */
    public boolean createXmlFile(Table table) {

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/TABLES/" + table.getTableId() + "/table.xml");

        boolean success = false;

        try {
            if (file.exists()) {
                System.out.println("XML Document with this name already exists !");
                return false;
            } else {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                //root element
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("table");
                doc.appendChild(rootElement);

//                //set attribute to staff element
//                Attr attr = doc.createAttribute("xsi:noNamespaceSchemaLocation");
//                attr.setValue("userSchema.xsd");
//                rootElement.setAttributeNode(attr);

//                //set attribute to staff element
//                Attr attr1 = doc.createAttribute("xlmns");
//                attr.setValue("");
//                rootElement.setAttributeNode(attr);
//
//                //set attribute to staff element
//                Attr attr2 = doc.createAttribute("xmlns:xsi");
//                attr.setValue("http://www.w3.org/2001/XMLSchema-instance");
//                rootElement.setAttributeNode(attr);
//
                //table_id element
                Element table_id = doc.createElement("table_id");
                table_id.appendChild(doc.createTextNode(Integer.toString(table.getTableId())));
                rootElement.appendChild(table_id);

                //places element
                Element places = doc.createElement("places");
                places.appendChild(doc.createTextNode(Integer.toString(table.getPlaces())));
                rootElement.appendChild(places);

                //vip element
                Element vip = doc.createElement("vip");
                vip.appendChild(doc.createTextNode(new Boolean(table.isVip()).toString()));
                rootElement.appendChild(vip);

                //write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                success = file.exists();
                System.out.println("XML document was created.");
                return success;
            }

        } catch (ParserConfigurationException pce) {
        } catch (TransformerException tfe) {
        }
        return success;
    }

    /**
     * Delete XML from directory TABLES/table/
     *
     * @param table
     * @return true if xml was deleted or false if not
     */
    public boolean deleteXmlFile(Table table) {

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/TABLES/" + table.getTableId() + "/table.xml");

        boolean success = false;
        try {
            if (file.exists()) {
                success = file.delete();
                System.out.println("XML document was removed.");
                return success;
            } else {
                System.out.println("XML Document with this name already exists !");
                return false;
            }
        } catch (Exception ex) {
        }
        return success;
    }

    /**
     * It returns collection of all tables
     *
     * @return all tables
     */
    public Collection<Table> allTables() {
        
        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        
        
        Collection<Table> tables = new ArrayList<Table>();
        Table table = new Table();
        File file = new File(initialFile + "/TABLES/");
        File[] files = file.listFiles();
        TableManager tm = new TableManagerImpl();

        for (int i = 0; i < files.length; i++) {
            int number = Integer.parseInt(files[i].getName());
            table = tm.findTableByID(number);
            tables.add(table);
        }
        return tables;
    }

    /**
     * It returns a collection of free tables
     *
     * @param date
     * @param time
     * @param duration
     * @return all free tables
     * @throws IllegalArgumentException if date is null
     * @throws IllegalArgumentException if time or date is negative
     */
    public Collection<Table> freeTables(User user, String date, int time, int duration) {
        if (date == null) {
            throw new IllegalArgumentException("Parameter date cannot be null");
        }
        if (time < 0) {
            throw new IllegalArgumentException("Time must be positive");
        }
        if (duration < 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        Collection<Table> allTables = new HashSet<Table>();
        allTables = allTables();
        Collection<Table> freeTables = new HashSet<Table>();

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/RESERVATIONS/" + date + ".xml");

        if (file.exists()) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = null;
                try {
                    doc = docBuilder.parse(file);
                } catch (SAXException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                Element reservationElement = doc.getDocumentElement();

                NodeList ids = reservationElement.getElementsByTagName("table_id");
                NodeList times = reservationElement.getElementsByTagName("time");
                NodeList durations = reservationElement.getElementsByTagName("duration");

                for (int i = 0; i < times.getLength(); i++) {
                    Element idEl = (Element) ids.item(i);
                    int idCont = Integer.parseInt(idEl.getTextContent());
                    Element timeEl = (Element) times.item(i);
                    int timeCont = Integer.parseInt(timeEl.getTextContent());
                    Element durationEl = (Element) durations.item(i);
                    int durationCont = Integer.parseInt(durationEl.getTextContent());

                    int cont = timeCont + durationCont;
                    int reservation = time + duration;

                    if ((timeCont >= time && reservation > timeCont) || (reservation > cont && time < cont) || (timeCont < time && reservation < cont)) {
                        Table t = findTableByID(idCont);
                        allTables.remove(t);//remove(findTableByID(idCont));
                    }
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

        if (!user.isVip()) {
            for (Table t : allTables) {
                if (!t.isVip()) {
                    freeTables.add(t);
                }
            }
            return freeTables;
        }
        return allTables;

    }
}