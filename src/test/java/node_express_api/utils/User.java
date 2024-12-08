package node_express_api.utils;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String firstName;
    private String lastName;
    private String age;
    private String id;

    public static Map<String, String> user1 = new HashMap<String, String>();
    public static Map<String, String> user2 = new HashMap<String, String>();
    public static Map<String, String> user3 = new HashMap<String, String>();
    public static Map<String, String> user4 = new HashMap<String, String>();

//        public static final String firstName = "John";
//        public static final String lastName = "Doe";
//        public static final String age = "46";
//
//
//        public static final String firstName = "Jane";
//        public static final String lastName = "Doe";
//        public static final String age = "35";
//
//        public static final String firstName = "Johnny";
//        public static final String lastName = "Doe";
//        public static final String age = "10";
//
//        public static final String firstName = "Jane";
//        public static final String lastName = "Unique";
//        public static final String age = "46";



    User(String firstName, String lastName, String age, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
