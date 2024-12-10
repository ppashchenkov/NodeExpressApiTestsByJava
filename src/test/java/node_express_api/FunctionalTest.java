package node_express_api;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import node_express_api.runner.BaseTest;
import node_express_api.utils.LoggerUtils;
import node_express_api.utils.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static node_express_api.utils.FuncTesData.*;
import static node_express_api.utils.TestData.*;
import static node_express_api.utils.TestData.user1;
import static node_express_api.utils.TestData.user2;
import static node_express_api.utils.TestData.user3;
import static node_express_api.utils.TestData.user4;

public class FunctionalTest extends BaseTest {
    @DataProvider(name = "searchUsers")
    public Object[][] searchUsers() {
        return new Object[][] {
                {tcName_1, searchCriteria_1, expectedCount_1, expectedUsers_1 },
                {tcName_2, searchCriteria_2, expectedCount_2, expectedUsers_2 },
                {tcName_3, searchCriteria_3, expectedCount_3, expectedUsers_3 },
                {tcName_4, searchCriteria_4, expectedCount_4, expectedUsers_4 },
                {tcName_5, searchCriteria_5, expectedCount_5, expectedUsers_5 },
                {tcName_6, searchCriteria_6, expectedCount_6, expectedUsers_6 }
        };
    }

    @Test
    public void testAddUser() {
        getPage().getByRole(AriaRole.LINK,
            new Page.GetByRoleOptions().setName(ADD_MENU).setExact(true)).click();
        getPage().getByLabel(LABEL_FIRST_NAME).fill(user1.getFirstName());
        getPage().getByLabel(LABEL_LAST_NAME).fill(user1.getLastName());
        getPage().getByLabel(LABEL_AGE).fill(user1.getAge());
        getPage().getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(BUTTON_ADD).setExact(true)).click();
        String actualFirstName = getPage().locator( "tbody>tr>td").first().innerText();
        String actualLastName = getPage().locator( "tbody>tr>td").nth(1).innerText();
        String actualAge = getPage().locator( "tbody>tr>td").nth(2).innerText();
        String id = getPage().locator( "tbody>tr>td").nth(3).innerText();
        user1.setId(id);

        assert(actualFirstName.equals(user1.getFirstName()));
        assert(actualLastName.equals(user1.getLastName()));
        assert(actualAge.equals(user1.getAge()));
        assert(user1.getId().length() == 36);
    }

    @Test
    public void testDeleteUser() {
        ArrayList<User> users = new ArrayList<>(Arrays.asList(user3));
        addUsers(users);

        List<ElementHandle> listTr = getPage().querySelectorAll("tr");

        assert(listTr.size() == 2);

        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(DELETE_MENU).setExact(true)).click();
        getPage().getByLabel(LABEL_USER_ID).fill(user3.getId());
        getPage().getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(BUTTON_DELETE).setExact(true)).click();
        listTr = getPage().querySelectorAll("tr");

        assert(listTr.size() == 1);
    }

    @Test(dataProvider = "searchUsers")
    public void testSearchUsers(String tcName, String[] searchCriteria, int expectedCount, User[] expectedUsers) {
        LoggerUtils.logSuccess("TestCase: " + tcName);
        ArrayList<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        addUsers(users);

        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(SEARCH_MENU).setExact(true)).click();
        getPage().getByLabel(LABEL_USER_ID).fill(searchCriteria[0]);
        getPage().getByLabel(LABEL_FIRST_NAME).fill(searchCriteria[1]);
        getPage().getByLabel(LABEL_LAST_NAME).fill(searchCriteria[2]);
        getPage().getByLabel(LABEL_AGE).fill(searchCriteria[3]);
        getPage().getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(BUTTON_SEARCH).setExact(true)).click();
        List<Locator> actualListSearchedUsers = getPage().locator("tbody>tr").all();
        int actualCountSearchedUsers = actualListSearchedUsers.size();

        Assert.assertEquals(actualCountSearchedUsers, expectedCount);

        for (int i = 0; i < actualCountSearchedUsers; i++) {
            String actualUserId = getPage().locator("tbody>tr").nth(i)
                    .locator("td").nth(3).innerText();
            String actualFirstUserName = getPage().locator("tbody>tr").nth(i)
                    .locator("td").nth(0).innerText();
            String actualLastUserName = getPage().locator("tbody>tr").nth(i)
                    .locator("td").nth(1).innerText();
            String actualAge = getPage().locator("tbody>tr").nth(i)
                    .locator("td").nth(2).innerText();

            Assert.assertEquals(actualFirstUserName, expectedUsers[i].getFirstName());
            Assert.assertEquals(actualLastUserName, expectedUsers[i].getLastName());
            Assert.assertEquals(actualAge, expectedUsers[i].getAge());
            Assert.assertEquals(actualUserId, expectedUsers[i].getId());
        }

//  чистим за собой
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(DELETE_MENU).setExact(true)).click();
        for (User user : users) {
            getPage().getByLabel(LABEL_USER_ID).fill(user.getId());
            getPage().getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName(BUTTON_DELETE).setExact(true)).click();
        }
        List<Locator> listAllUsers = getPage().locator("tr").all();

        Assert.assertEquals(listAllUsers.size(), 1);
    }
}
