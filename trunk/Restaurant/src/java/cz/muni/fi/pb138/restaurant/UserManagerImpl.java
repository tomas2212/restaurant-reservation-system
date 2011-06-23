/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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
 * This class manages all operations with user.
 * @author Jakub Papcun, Tomas Svrcek and team
 */
public class UserManagerImpl implements UserManager {

    private User user;

    /**
     * Add user to directory USERS/
     *
     * @param user an user
     * @return true if user was addes or false if not
     */
    public boolean addUser(User user) {
        boolean d = createDirectory(user);
        boolean u = usersReservationsXml(user);
        boolean c = createXmlFile(user);
        return (d && u && c);
    }

    /**
     * Create directory in USERS/
     *
     * @param user an user
     * @return true if directory was successful created or false if not
     */
    public boolean createDirectory(User user) {
        boolean success = false;

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/USERS/" + user.getEmail());

        try {
            if (file.exists()) {
                System.out.println("Directory with this name already exists !");
                return false;
            } else {
                success = file.mkdir();
                if (success) {
                    System.out.println("Directory: " + user.getEmail() + " was created");
                    return true;
                } else {
                    System.out.println("Error when trying to create the directory" + user.getEmail());
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
     * Create XML file in directory USERS/user/
     *
     * @param user an user
     * @return true if XML file ws created or false if not
     */
    public boolean createXmlFile(User user) {
        boolean success = false;

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/USERS/" + user.getEmail() + "/user.xml");

        try {
            if (file.exists()) {
                System.out.println("XML Document with this name already exists !");
                return false;
            } else {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                //root element
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("user");
                doc.appendChild(rootElement);

                //firstname element
                Element firstname = doc.createElement("first_name");
                firstname.appendChild(doc.createTextNode(user.getFirstname()));
                rootElement.appendChild(firstname);

                //surname element
                Element surname = doc.createElement("surname");
                surname.appendChild(doc.createTextNode(user.getSurname()));
                rootElement.appendChild(surname);

                //email element
                Element email = doc.createElement("e-mail_id");
                email.appendChild(doc.createTextNode(user.getEmail()));
                rootElement.appendChild(email);

                //password element
                Element password = doc.createElement("password");
                password.appendChild(doc.createTextNode(user.getPassword()));
                rootElement.appendChild(password);

                //vip element
                Element vip = doc.createElement("vip");
                vip.appendChild(doc.createTextNode(new Boolean(user.isVip()).toString()));
                rootElement.appendChild(vip);

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
            Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, pce);
        } catch (TransformerException tfe) {
            Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, tfe);
        }
        return success;
    }

    /**
     * Create a XML file with user's reservations
     *
     * @param user an user
     * @return true if XML user reservations was created or false if not
     */
    public boolean usersReservationsXml(User user) {
        boolean success = false;

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/USERS/" + user.getEmail() + "/reservations.xml");

        try {
            if (file.exists()) {
                System.out.println("XML Document with this name already exists !");
                return false;
            } else {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                //root element
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("reservations");
                rootElement.setAttribute("user_id", user.getEmail());
                doc.appendChild(rootElement);


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
            Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, pce);
        } catch (TransformerException tfe) {
            Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, tfe);
        }
        return success;
    }

    /**
     * Delete user from USERS/
     *
     * @param user an user
     * @return true if user was deleted or false if not
     */
    public boolean deleteUser(User user) {
        deleteAllUsersReservations(user);
        deleteXmlFile(user, "user.xml");
        deleteXmlFile(user, "reservations.xml");
        return deleteDirectory(user);
    }

    /**
     * Delete xml file from USERS/user/
     *
     * @param user an user
     * @param xmlFile name of xml file
     * @return true if XML file was deleted or false if not
     */
    public boolean deleteXmlFile(User user, String xmlFile) {
        boolean success = false;

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/USERS/" + user.getEmail() + "/" + xmlFile);

        try {
            if (file.exists()) {
                success = file.delete();
                System.out.println("USERS/" + user.getEmail() + "/" + xmlFile + " was removed.");
                return success;
            } else {
                System.out.println("XML Document with this name doesn't exists !");
                return false;
            }
        } catch (Exception ex) {
        }
        return success;
    }

    /**
     * Delete all users's reservations [delete all xml files(reservations) from USERS/user/]
     *
     * @param user an user
     * @return true if all user's reservations was deleted or false if not
     */
    public boolean deleteAllUsersReservations(User user) {
        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/RESERVATIONS/");
        File[] files = file.listFiles();
        boolean success = false;

        for (int i = 0; i < (files.length - 1); i++) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = null;
                try {
                    doc = docBuilder.parse(new File(file.getAbsolutePath()+ "/" + files[i].getName()));
                } catch (SAXException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                NodeList res = doc.getElementsByTagName("reservation");
                NodeList userIds = doc.getElementsByTagName("user_id");
                for (int j = 0; j < userIds.getLength(); j++) {
                    Element id = (Element) userIds.item(j);
                    String idStr = id.getTextContent();
                    if (idStr.equals(user.getEmail())) {
                        id.getParentNode().getParentNode().removeChild(res.item(j));
                        j--;
                        try {
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(new File(file.getAbsolutePath() + "/" + files[i].getName()));
                            transformer.transform(source, result);
                        } catch (Exception ex) {
                            return false;
                        }
                        success = new File(file.getAbsolutePath() + "/" + files[i].getName()).exists();
                        System.out.println("Reservation was removed.");
                    }
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                return false;
            }
        }
        return success;
    }

    /**
     * Delete directory from USERS/
     *
     * @param user an user
     * @return true if directory was deleted or false if not
     */
    public boolean deleteDirectory(User user) {
        boolean success = false;

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/USERS/" + user.getEmail());

        try {
            if (file.exists()) {
                success = file.delete();
                System.out.println("Directory " + user.getEmail() + " was removed !");
                return success;
            } else {
                System.out.println("Directory with this name doesn't exists !");
                return false;
            }
        } catch (Exception e) {
        } finally {
            if (success) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Update file user.xml, his reservations must be the same
     *
     * @param user an user
     * @return true if user was updated or false if not
     */
    public boolean updateUser(User user) {
        boolean userDeletion = deleteXmlFile(user, "user.xml");
        return (userDeletion && createXmlFile(user));
    }

    /**
     * Find user by email address
     *
     * @param email e-mail string
     * @return user when email exists or null if doesn't
     */
    public User findUser(String email) {

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
                    doc = docBuilder.parse(new File(file.getAbsolutePath() + "/user.xml"));
                } catch (SAXException se) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, se);
                } catch (IOException ioe) {
                    Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ioe);
                }
                Element userElement = doc.getDocumentElement();

                NodeList firstnames = userElement.getElementsByTagName("first_name");
                Element first = (Element) firstnames.item(0);
                String firstname = first.getTextContent();

                NodeList surnames = userElement.getElementsByTagName("surname");
                Element sur = (Element) surnames.item(0);
                String surname = sur.getTextContent();

                NodeList emails = userElement.getElementsByTagName("e-mail_id");
                Element e = (Element) emails.item(0);
                String mail = e.getTextContent();

                NodeList passwords = userElement.getElementsByTagName("password");
                Element pass = (Element) passwords.item(0);
                String password = pass.getTextContent();

                NodeList vips = userElement.getElementsByTagName("vip");
                Element v = (Element) vips.item(0);
                String vipStr = v.getTextContent();
                boolean vip = Boolean.parseBoolean(vipStr);

                User newUser = new User(vip);
                newUser.setFirstname(firstname);
                newUser.setSurname(surname);
                newUser.setPassword(password);
                newUser.setEmail(email);

                return newUser;
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            System.out.println("User with this e-mail doesn't exist.");
            return null;
        }
    }

    /**
     * It returns collection of all users
     *
     * @return collection of users
     */
    public Collection<User> allUsers() {

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }

        Set<User> users = new HashSet<User>();
        User user = new User();
        File file = new File(initialFile + "/USERS/");
        File[] files = file.listFiles();
        UserManager um = new UserManagerImpl();
        for (int i = 0; i < files.length; i++) {
            user = um.findUser(files[i].getName());
            users.add(user);
        }
        return users;
    }

    /**
     * It returns all users's reservations
     *
     * @param user an user
     * @return collection of user's reservations
     */
    public Collection<Reservation> allUsersReservations(User user) {
        TableManager tm = new TableManagerImpl();

        String currentPath = this.getClass().getResource("/").getPath();
        File initialFile = new File(currentPath);
        for (int i = 0; i < 4; i++) {
            initialFile = initialFile.getParentFile();
        }
        File file = new File(initialFile + "/USERS/" + user.getEmail() + "/reservations.xml");
        Set<Reservation> reservations = new HashSet<Reservation>();

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

            NodeList reservationIds = doc.getElementsByTagName("reservation_id");
            NodeList dates = doc.getElementsByTagName("date");
            NodeList times = doc.getElementsByTagName("time");
            NodeList durations = doc.getElementsByTagName("duration");
            NodeList tableIds = doc.getElementsByTagName("table_id");
            for (int i = 0; i < reservationIds.getLength(); i++) {
                Reservation reservation = new Reservation();

                int reservationId = Integer.parseInt(reservationIds.item(i).getTextContent());
                int tableId = Integer.parseInt(tableIds.item(i).getTextContent());
                Table table = tm.findTableByID(tableId);
                String date = dates.item(i).getTextContent();
                int time = Integer.parseInt(times.item(i).getTextContent());
                int duration = Integer.parseInt(durations.item(i).getTextContent());

                reservation.setReservationId(reservationId);
                reservation.setUser(user);
                reservation.setTable(table);
                reservation.setDate(date);
                reservation.setTime(time);
                reservation.setDuration(duration);

                reservations.add(reservation);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return Collections.unmodifiableCollection(reservations);
    }
}
