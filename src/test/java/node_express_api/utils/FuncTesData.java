package node_express_api.utils;

import static node_express_api.utils.TestData.*;

public class FuncTesData {
    public static final String tcName_1 = "Unique First Name";
    public static final String[] searchCriteria_1 = new String[]{"", user1.getFirstName(), "", ""};
    public static final int expectedCount_1 = 1;
    public static final User[] expectedUsers_1 = new User[]{user1};

    public static final String tcName_2 = "Unique Last Name";
    public static final String[] searchCriteria_2 = new String[]{"", "", user4.getLastName(), ""};
    public static final int expectedCount_2 = 1;
    public static final User[] expectedUsers_2 = new User[]{user4};

    public static final String tcName_3 = "Unique Age";
    public static final String[] searchCriteria_3 = new String[]{"", "", "", user3.getAge()};
    public static final int expectedCount_3 = 1;
    public static final User[] expectedUsers_3 = new User[]{user3};

    public static final String tcName_4 = "Non-Unique First Name";
    public static final String[] searchCriteria_4 = new String[]{"", user2.getFirstName(), "", ""};
    public static final int expectedCount_4 = 2;
    public static final User[] expectedUsers_4 = new User[]{user2, user4};

    public static final String tcName_5 = "Non-Unique Last Name";
    public static final String[] searchCriteria_5 = new String[]{"", "", user3.getLastName(), ""};
    public static final int expectedCount_5 = 3;
    public static final User[] expectedUsers_5 = new User[]{user1, user2, user3};

    public static final String tcName_6 = "Non-Unique Age";
    public static final String[] searchCriteria_6 = new String[]{"", "", "", user1.getAge()};
    public static final int expectedCount_6 = 2;
    public static final User[] expectedUsers_6 = new User[]{user1, user4};
}
