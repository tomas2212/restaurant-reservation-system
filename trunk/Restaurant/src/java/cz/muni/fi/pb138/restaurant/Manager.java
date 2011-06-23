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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class which manages all operations with reservations.
 *
 * @author Jakub Papcun, Tomas Svrcek and team
 */
public class Manager {

    private UserManager um;
    private TableManager tm;

    /**
     * Constructor
     *
     * @param um userManager
     * @param tm tableManager
     */
    public Manager(UserManager um, TableManager tm) {
        this.um = um;
        this.tm = tm;
    }

    public Manager() {
        um = new UserManagerImpl();
        tm = new TableManagerImpl();
    }

    public TableManager getTm() {
        return tm;
    }

    public UserManager getUm() {
        return um;
    }

    /**
     * Create reservation
     *
     * @param reservation reservation
     * @return true if reservation was created or false if not
     */
    public void createReservation(Reservation reservation) {
        reservation.setReservationId(getId());
        createXmlFile(reservation);
        createUsersReservation(reservation, reservation.getUser());
    }

    /**
     * Create XML file
     *
     * @param reservation reservation
     * @return true if XML file was created or false if not
     */
    public boolean createXmlFile(Reservation reservation) {
        boolean success = false;
        try {
            Document doc = null;
            Element childElement = null;

            String currentPath = this.getClass().getResource("/").getPath();
            File initialFile = new File(currentPath);
            for (int i = 0; i < 4; i++) {
                initialFile = initialFile.getParentFile();
            }
            File file = new File(initialFile + "/RESERVATIONS/" + reservation.getDate() + ".xml");

            if (file.exists()) {


                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                try {
                    doc = docBuilder.parse(file);
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
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            boolean valid = validation(file, new File(initialFile + "/SCHEMAS/reservationsSchema.xsd"));

            success = file.exists();
            System.out.println("XML document was created.");
            return (valid && success);

        } catch (ParserConfigurationException pce) {
        } catch (TransformerException tfe) {
        }
        return success;
    }

    /**
     * Create user's reservation in directory USERS/user/
     *
     * @param reservation a reservation
     * @param user an user
     * @return true if reservation was created or false if not
     */
    public boolean createUsersReservation(Reservation reservation, User user) {
        boolean success = false;
        Document doc = null;

        String currentPath = this.getClass().getResource("/").toString();
        currentPath = currentPath.substring(6, currentPath.length());
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/USERS/" + user.getEmail() + "/reservations.xml");

        try {
            if (file.exists()) {

                String filepath = file.getAbsolutePath();
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
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                boolean valid = validation(file, new File(initialFile + "/SCHEMAS/usersReservationsSchema.xsd"));

                success = (valid && file.exists());
                System.out.println("XML document was created.");
            }
        } catch (Exception ex) {
        }
        return success;
    }

    /**
     * Control password before logging in
     *
     * @param email string
     * @param password string
     * @return true if password is correct or false if not
     * @throws IllegalArgumentException if email or password is null
     */
    public boolean login(String email, String password) throws IOException {
        if (email == null) {
            throw new IllegalArgumentException("Parameter email cannot be null!");
        }
        if (password == null) {
            throw new IllegalArgumentException("Parameter password cannot be null!");
        }
        boolean success = false;

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/USERS/" + email);

        if (file.exists()) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = null;
                try {
                    doc = docBuilder.parse(new File(file + file.separator + "user.xml"));
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

    /**
     * Delete reservation from directory RESERVATIONS/
     *
     * @param reservationId a reservation ID
     * @return true if resrvation was deleted or false if not
     */
    public boolean deleteReservation(int reservationId) {
        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/RESERVATIONS/");
        File[] files = file.listFiles();

        for (int i = 1; i < files.length; i++) {
            if (files[i].getName().matches("20[0-9][0-9]-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1]).xml")) {
                try {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    Document doc = null;
                    try {
                        doc = docBuilder.parse(new File(file.getAbsolutePath() + "/" + files[i].getName()));
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
                                StreamResult result = new StreamResult(new File(file.getAbsolutePath() + "/" + files[i].getName()));
                                transformer.transform(source, result);
                            } catch (Exception ex) {
                            }
                            boolean valid = validation(new File(initialFile + "/RESERVATIONS/" + files[i].getName()), new File(initialFile + "/SCHEMAS/reservationsSchema.xsd"));

                            boolean exists = new File(file.getAbsolutePath() + "/" + files[i].getName()).exists();
                            boolean removed = removeUsersReservation(reservationId, userId);
                            System.out.println("Reservation was removed.");
                            return (valid && exists && removed);
                        }
                    }
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Delete user's reservation
     *
     * @param reservationId a reservation ID
     * @param userId an user ID
     * @return true if reservation was deleted from user or false if not
     */
    public boolean removeUsersReservation(int reservationId, String userId) {

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/USERS/" + userId + "/reservations.xml");

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
            boolean valid = false;
            NodeList reservations = doc.getElementsByTagName("reservation");
            NodeList resIds = doc.getElementsByTagName("reservation_id");
            for (int i = 0; i < resIds.getLength(); i++) {
                Element resId = (Element) resIds.item(i);
                if (Integer.parseInt(resId.getTextContent()) == reservationId) {
                    resId.getParentNode().getParentNode().removeChild(reservations.item(i));
                    try {
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(file);
                        transformer.transform(source, result);
                        valid = validation(file, new File(initialFile + "/SCHEMAS/usersReservationsSchema.xsd"));
                    } catch (Exception ex) {
                    }


                    return (valid && file.exists());
                }
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Get id from RESERVATIONS/id.dat
     *
     * @return number of ID or -1 if doesn't exist
     */
    public int getId() {
        int id = 0;

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        try {

            File file = new File(initialFile + "/RESERVATIONS/id.dat");
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

    /**
     * Validates created XML document
     * @param xml document to be validated
     * @param xsd XML schema to validate with
     * @return true if valid
     */
    public boolean validation(File xml, File xsd) {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(xsd);
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        } catch (SAXException ex) {
            Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, "Document is invalid", ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, "No such file", ex);
            return false;
        }
    }
}
