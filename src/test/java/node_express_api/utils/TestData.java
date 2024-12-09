package node_express_api.utils;

public class TestData {
    public static final String BASE_URL = "http://localhost:5000";
    public static final String API_USERS_URL = "http://localhost:5000/api/users";
    public static final String HOME_END_POINT = "/";
    public static final String ADD_END_POINT = "add";
    public static final String SEARCH_END_POINT = "search";
    public static final String EDIT_END_POINT = "edit";
    public static final String DELETE_END_POINT = "delete";
    public static final String USERS_END_POINT = "/users/";

    public static final String ADD_TITLE = "Users app";
    public static final String SEARCH_TITLE = "Search user";
    public static final String EDIT_TITLE = "Edit user";
    public static final String DELETE_TITLE = "Delete user";

    public static final String H1_CONTENT = "Node Express API Server App";
    public static final String H2_ADD_TITLE = "Add User";
    public static final String H2_SEARCH_TITLE = "Search user";
    public static final String H2_EDIT_TITLE = "Edit user";
    public static final String H2_DELETE_TITLE = "Delete user";

    public static final String ADD_MENU = "Add";
    public static final String SEARCH_MENU = "Search";
    public static final String EDIT_MENU = "Edit";
    public static final String DELETE_MENU = "Delete";

    public static final String LABEL_FIRST_NAME = "First Name";
    public static final String LABEL_LAST_NAME = "Last Name";
    public static final String LABEL_AGE = "Age";
    public static final String LABEL_USER_ID = "User ID";

    public static final String BUTTON_ADD = "Add";
    public static final String BUTTON_DELETE = "Delete";

    public static User user1 = new User(
            "John",
            "Doe",
            "46",
            "");
    public static User user2 = new User(
            "Jane",
            "Doe",
            "35",
            "");
    public static User user3 = new User(
            "Johnny",
            "Doe",
            "10",
            "");
    public static User user4 = new User(
            "Jane",
            "Unique",
            "46",
            "");
}
