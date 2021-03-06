package at.helpers;

import at.models.Organization;
import at.models.User;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Environment {
    public String name;
    private Document document;
    public Map<String, String> urls;
    public Map<String, Map<String, String>> databases=new HashMap<>();
    public Organization[] organizations;
    public User[] users;
    private Element environment;

    private Environment(File xml,String environmentName) {
        this.setDocument(xml);
        this.getProperties(environmentName);
    }
    public static Environment getProperties(File xml, String environment) {
        return new Environment(xml, environment);
    }
    public void getProperties(String environment) {
        name=environment;
        this.getEnvironment(environment);
        this.getUrls();
        this.getDatabaseProperties();
        this.getUsers();
        this.getOrganizations();
    }

    private void getOrganizations() {
        NodeList nodeList = this.environment.getElementsByTagName("organizations");
        if (nodeList != null && nodeList.getLength() >= 1) {
            NodeList organizations = ((Element) nodeList.item(0)).getElementsByTagName("organization");
            this.organizations = new Organization[organizations.getLength()];
            for (int i = 0; i < organizations.getLength(); ++i) {
                Node org = organizations.item(i);
                this.organizations[i] = new Organization(this.getTagAttribute(org, "alias"),
                        this.getTagValue(org, "inn"),
                        this.getTagValue(org, "ogrn"),
                        this.getTagValue(org, "name"),
                        this.getTagValue(org, "regionCD"),
                        this.getTagValue(org, "actTypeCD"));
            }
        } else {
            System.out.println("Tag 'organizations' is undefined");
            Assert.fail("???????????? ?????????????????????? ???? ??????????????????!");
        }
    }

    private void getUsers() {
        NodeList nodeList = this.environment.getElementsByTagName("users");
        if (nodeList != null && nodeList.getLength() >= 1) {
            NodeList users = ((Element) nodeList.item(0)).getElementsByTagName("user");
            this.users = new User[users.getLength()];
            for (int i = 0; i < users.getLength(); ++i) {
                Node user = users.item(i);
                this.users[i] = new User(this.getTagAttribute(user, "alias"),
                        this.getTagValue(user, "name"),
                        this.getTagValue(user, "password"),
                        this.getTagValue(user, "role"));
            }
        } else {
            System.out.println("Tag 'users' is undefined");
            Assert.fail("???????????? ?????????????????????????? ???? ??????????????????!");
        }
    }

    private void getDatabaseProperties() {
        NodeList nodeList = this.environment.getElementsByTagName("databases");
        if (nodeList != null && nodeList.getLength() >= 1) {
            NodeList databases = ((Element) nodeList.item(0)).getElementsByTagName("database");

            for (int i = 0; i < databases.getLength(); ++i) {
                Node database = databases.item(i);
                Map<String, String> databaseParameters = new HashMap();
                databaseParameters.put("driver", this.getTagValue(database, "driver"));
                databaseParameters.put("host", this.getTagValue(database, "host"));
                databaseParameters.put("port", this.getTagValue(database, "port"));
                databaseParameters.put("service", this.getTagValue(database, "service"));
                databaseParameters.put("login", this.getTagValue(database, "login"));
                databaseParameters.put("password", this.getTagValue(database, "password"));
                databaseParameters.put("schema", this.getTagValue(database, "schema"));
                this.databases.put(this.getTagAttribute(database, "alias"), databaseParameters);
            }

        } else {
            System.out.println("Tag 'databases' is undefined");
            Assert.fail("?????????????????? ?????? ???????????? ???? ????????????????????!");
        }
    }

    private void getEnvironment(String environment) {
        Element root = this.document.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("environment");
        if (nodeList != null && nodeList.getLength() >= 1) {
            for(int i = 0; i < nodeList.getLength(); ++i) {
                Element element = (Element)nodeList.item(i);
                String name = element.getAttribute("name");
                if (name != null && name.equalsIgnoreCase(environment)) {
                    this.environment = element;
                }
            }
            if (this.environment == null) {
                System.out.println("Environment '" + environment + "' is not found");
                Assert.fail("???????????????? ?????????????????? '" + environment + "' ???? ??????????????!");
            }
        } else {
            System.out.println("Tag 'environment' is undefined");
            Assert.fail("???????????????? ?????????????????? ???? ????????????????????!");
        }
    }

    private void getUrls() {
        NodeList nodeList = this.environment.getElementsByTagName("endpoints");
        if (nodeList != null && nodeList.getLength() >= 1) {
            NodeList urls = ((Element) nodeList.item(0)).getElementsByTagName("endpoint");
            this.urls = new HashMap<>();
            for (int i = 0; i < urls.getLength(); ++i) {
                Node url = urls.item(i);
                this.urls.put(this.getTagAttribute(url, "alias"),
                        this.getTagValue(url));
            }
        } else {
            System.out.println("Tag 'endpoints' is undefined");
            Assert.fail("???????????? endpoints ???? ??????????????????!");
        }
    }

    private void setDocument(File xml) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            this.document = documentBuilder.parse(xml);
        } catch (Exception var4) {
            System.out.println("Parse Environment XML failed");
            var4.printStackTrace();
            Assert.fail("???? ?????????????? ???????????????????? XML ???????????????? ?? ?????????????????????? ?????????????????? ??????????????????!");
        }
    }

    private String getTagValue(Node node, String tagName) {
        NodeList nodeList = ((Element) node).getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() >= 1) {
            return this.getTagValue(nodeList.item(0));
        } else {
            System.out.println("Tag by name '" + tagName + "' is not found");
            Assert.fail("?????? '" + tagName + "' ???? ????????????!");
            return null;
        }
    }

    private String getTagValue(Node node) {
        return node.getFirstChild() != null ? node.getFirstChild().getNodeValue() : null;
    }

    private String getTagAttribute(Node node, String attribute) {
        return ((Element) node).getAttribute(attribute);
    }

}
