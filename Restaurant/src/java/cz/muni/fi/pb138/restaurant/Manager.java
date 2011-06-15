/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.restaurant;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author xsvrcek1
 */
public class Manager {

    private UserManager um;
    private TableManager tm;

    public Manager(UserManager um, TableManager tm) {
        this.um = um;
        this.tm = tm;
    }

    public TableManager getTm() {
        return tm;
    }

    public UserManager getUm() {
        return um;
    }

    /*public Reservation newReservation(User user, Table table) {
    return new Reservation(user, table);
    }*/
    public void createReservation(Reservation reservation) {
        reservation.setReservationId(getId());
        createXmlFile(reservation);
        createUsersReservation(reservation, reservation.getUser());

    }

    public boolean createXmlFile(Reservation reservation) {
        boolean success = false;
        try {
            Document doc = null;
            Element childElement = null;

            if (new File("RESERVATIONS/" + reservation.getDate() + ".xml").exists()) {

                String filepath = "RESERVATIONS/" + reservation.getDate() + ".xml";
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                try {
                    doc = docBuilder.parse(filepath);
                } catch (SAXException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                Node reservations = doc.getFirstChild();

                //append a new node
                childElement = doc.createElement("reservation");
                reservations.appendChild(childElement);

            } else {

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                //root element
                doc = docBuilder.newDocument();

                Element rootElement = doc.createElement("reservations");
                rootElement.setAttribute("date", reservation.getDate());
                doc.appendChild(rootElement);

                //child element
                childElement = doc.createElement("reservation");
                rootElement.appendChild(childElement);


            }
            //reservation_id
            Element reservationId = doc.createElement("reservation_id");
            reservationId.appendChild(doc.createTextNode(Integer.toString(reservation.getReservationId())));
            childElement.appendChild(reservationId);

            //time element
            Element time = doc.createElement("time");
            time.appendChild(doc.createTextNode(Integer.toString(reservation.getTime())));
            childElement.appendChild(time);

            //duration element
            Element duration = doc.createElement("duration");
            duration.appendChild(doc.createTextNode(Integer.toString(reservation.getDuration())));
            childElement.appendChild(duration);

            //user_id element
            Element userId = doc.createElement("user_id");
            userId.appendChild(doc.createTextNode(reservation.getUser().getEmail()));
            childElement.appendChild(userId);

            //table_id element
            Element tableId = doc.createElement("table_id");
            tableId.appendChild(doc.createTextNode(Integer.toString(reservation.getTable().getTableId())));
            childElement.appendChild(tableId);

            //write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("RESERVATIONS/" + reservation.getDate() + ".xml"));
            transformer.transform(source, result);

            success = new File("RESERVATIONS/" + reservation.getDate() + ".xml").exists();
            System.out.println("XML document was created.");
            return success;

        } catch (ParserConfigurationException pce) {
        } catch (TransformerException tfe) {
        }
        return success;
    }

    public boolean createUsersReservation(Reservation reservation, User user) {
        boolean success = false;
        Document doc = null;
        try {
            if (new File("USERS/" + user.getEmail() + "/reservations.xml").exists()) {

                String filepath = "USERS/" + user.getEmail() + "/reservations.xml";
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                try {
                    doc = docBuilder.parse(filepath);
                } catch (SAXException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                Node reservations = doc.getFirstChild();

                //append a new node
                Element childElement = doc.createElement("reservation");
                reservations.appendChild(childElement);

                //reservation_id
                Element reservationId = doc.createElement("reservation_id");
                reservationId.appendChild(doc.createTextNode(Integer.toString(reservation.getReservationId())));
                childElement.appendChild(reservationId);

                //date element
                Element userId = doc.createElement("date");
                userId.appendChild(doc.createTextNode(reservation.getDate()));
                childElement.appendChild(userId);

                //time element
                Element time = doc.createElement("time");
                time.appendChild(doc.createTextNode(Integer.toString(reservation.getTime())));
                childElement.appendChild(time);

                //duration element
                Element duration = doc.createElement("duration");
                duration.appendChild(doc.createTextNode(Integer.toString(reservation.getDuration())));
                childElement.appendChild(duration);

                //table_id element
                Element tableId = doc.createElement("table_id");
                tableId.appendChild(doc.createTextNode(Integer.toString(reservation.getTable().getTableId())));
                childElement.appendChild(tableId);

                //write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("USERS/" + user.getEmail() + "/reservations.xml"));
                transformer.transform(source, result);

                success = new File("USERS/" + user.getEmail() + "/reservations.xml").exists();
                System.out.println("XML document was created.");
            }
        } catch (Exception ex) {
        }
        return success;
    }

    public boolean login(String email, String password) {
        if (email == null) {
            throw new IllegalArgumentException("Parameter email cannot be null!");
        }
        if (password == null) {
            throw new IllegalArgumentException("Parameter password cannot be null!");
        }
        boolean success = false;
        if (new File("USERS/" + email).exists()) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = null;
                try {
                    doc = docBuilder.parse(new File("USERS/" + email + "/user.xml"));
                } catch (SAXException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                Element userElement = doc.getDocumentElement();

                NodeList passwords = userElement.getElementsByTagName("password");
                Element pass = (Element) passwords.item(0);
                String password2 = pass.getTextContent();

                if (password.equals(password2)) {
                    success = true;
                }
            } catch (ParserConfigurationException pce) {
                Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, pce);
            }
        }
        return success;
    }

    public boolean deleteReservation(int reservationId) {
        File file = new File("RESERVATIONS/");
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = null;
                try {
                    doc = docBuilder.parse(new File("RESERVATIONS/" + files[i].getName()));
                } catch (SAXException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                NodeList res = doc.getElementsByTagName("reservation");
                NodeList reservationIds = doc.getElementsByTagName("reservation_id");
                NodeList userIds = doc.getElementsByTagName("user_id");
                for (int j = 0; j < reservationIds.getLength(); j++) {
                    Element id = (Element) reservationIds.item(j);
                    int idInt = Integer.parseInt(id.getTextContent());
                    if (idInt == reservationId) {
                        String userId = userIds.item(j).getTextContent();
                        id.getParentNode().getParentNode().removeChild(res.item(j));
                        try {
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(new File("RESERVATIONS/" + files[i].getName()));
                            transformer.transform(source, result);
                        } catch (Exception ex) {
                        }

                        boolean exists = new File("RESERVATIONS/" + files[i].getName()).exists();
                        boolean removed = removeUsersReservation(reservationId, userId);
                        System.out.println("Reservation was removed.");
                        return (exists && removed);

                    }
                }


            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean removeUsersReservation(int reservationId, String userId) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = null;
            try {
                doc = docBuilder.parse(new File("USERS/" + userId + "/reservations.xml"));
            } catch (SAXException ex) {
                Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            NodeList reservations = doc.getElementsByTagName("reservation");
            NodeList resIds = doc.getElementsByTagName("reservation_id");
            for(int i = 0; i<resIds.getLength(); i++){
                Element resId = (Element) resIds.item(i);
                if( Integer.parseInt(resId.getTextContent()) == reservationId ){
                    resId.getParentNode().getParentNode().removeChild(reservations.item(i));
                    try {
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(new File("USERS/" + userId + "/reservations.xml"));
                            transformer.transform(source, result);
                        } catch (Exception ex) {
                        }

                        return (new File("USERS/" + userId + "/reservations.xml").exists());
                }
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getId() {
        int id = 0;
        try {
            File file = new File("RESERVATIONS/id.dat");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            id = Integer.parseInt(br.readLine());
            br.close();

            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file, false));
            dos.writeBytes(Integer.toString(id + 1));
            dos.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("No such file (id.dat)");
            return -1;
        } catch (IOException ioe) {
        }
        return id;
    }
}