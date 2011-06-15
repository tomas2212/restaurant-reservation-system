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
 *
 * @author xsvrcek1
 */
public class UserManagerImpl implements UserManager {

    private User user;

    public boolean createDirectory(User user) {
        boolean success = false;
        try {
            if (new File("USERS/" + user.getEmail()).exists()) {
                System.out.println("Directory with this name already exists !");
                return false;
            } else {
                success = (new File("USERS/" + user.getEmail())).mkdir();
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

    public boolean deleteDirectory(User user) {
        boolean success = false;
        try {
            if (new File("USERS/" + user.getEmail()).exists()) {
                success = new File("USERS/" + user.getEmail()).delete();
                System.out.println("Directory " + user.getEmail() + " was removed !");
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

    public boolean addUser(User user) {
        return (createDirectory(user) && usersReservationsXml(user) && createXmlFile(user));
    }

    public boolean deleteUser(User user) {
        deleteAllUsersReservations(user);
        deleteXmlFile(user, "user.xml");
        deleteXmlFile(user, "reservations.xml");
        deleteDirectory(user);
        return true;
    }

    public boolean updateUser(User user) {
        deleteUser(user);
        return addUser(user);
    }

    public User findUser(String email) {
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

    public boolean createXmlFile(User user) {

        boolean success = false;

        try {
            if (new File("USERS/" + user.getEmail() + "/user.xml").exists()) {
                System.out.println("XML Document with this name already exists !");
                return false;
            } else {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                //root element
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("user");
                doc.appendChild(rootElement);
                /*rootElement.setAttribute("xsi:noNameSpaceSchemaLocation", "userSchema.xsd");
                rootElement.setAttribute("xmlns", "");
                rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");*/

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

                //write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("USERS/" + user.getEmail() + "/user.xml"));
                transformer.transform(source, result);

                success = new File("USERS/" + user.getEmail() + "/user.xml").exists();
                System.out.println("XML document was created.");
                return success;
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return success;
    }

    public boolean deleteXmlFile(User user, String xmlFile) {
        boolean success = false;

        try {
            if (new File("USERS/" + user.getEmail() + "/" + xmlFile).exists()) {
                success = new File("USERS/" + user.getEmail() + "/" + xmlFile).delete();
                System.out.println("XML document was removed.");
                return success;
            } else {
                System.out.println("XML Document with this name doesn't exists !");
                return false;
            }
        } catch (Exception ex) {
        }
        return success;
    }

    public boolean deleteAllUsersReservations(User user) {
        File file = new File("RESERVATIONS/");
        File[] files = file.listFiles();
        boolean success = false;

        for (int i = 0; i < (files.length - 1); i++) {
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
                NodeList userIds = doc.getElementsByTagName("user_id");
                for (int j = 0; j < userIds.getLength(); j++) {
                    Element id = (Element) userIds.item(j);
                    String idStr = id.getTextContent();
                    if (idStr.equals(user.getEmail())) {

                        id.getParentNode().getParentNode().removeChild(res.item(j));
                        try {
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(new File("RESERVATIONS/" + files[i].getName()));
                            transformer.transform(source, result);
                        } catch (Exception ex) {
                            return false;
                        }
                        success = new File("RESERVATIONS/" + files[i].getName()).exists();
                        System.out.println("Reservation was removed.");
                    }
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UserManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return success;
    }

    public Collection<User> allUsers() {
        Set<User> users = new HashSet<User>();
        User user = new User();
        File file = new File("USERS/");
        File[] files = file.listFiles();
        String[] names = null;
        UserManager um = new UserManagerImpl();

        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        for (int i = 0; i < names.length; i++) {
            user = um.findUser(names[i]);
            users.add(user);
        }
        return users;
    }

    public boolean usersReservationsXml(User user) {
        boolean success = false;

        try {
            if (new File("USERS/" + user.getEmail() + "/reservations.xml").exists()) {
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
                StreamResult result = new StreamResult(new File("USERS/" + user.getEmail() + "/reservations.xml"));
                transformer.transform(source, result);

                success = new File("USERS/" + user.getEmail() + "/reservations.xml").exists();
                System.out.println("XML document was created.");
                return success;
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return success;
    }

    public Collection<Reservation> allUsersReservations(User user) {
        TableManager tm = new TableManagerImpl();
        File file = new File("USERS/" + user.getEmail() + "/reservations.xml");
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