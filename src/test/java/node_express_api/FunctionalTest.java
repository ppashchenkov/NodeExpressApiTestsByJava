package node_express_api;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import node_express_api.runner.BaseTest;
import node_express_api.utils.LoggerUtils;
import node_express_api.utils.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static node_express_api.utils.TestData.*;
import static node_express_api.utils.TestData.user1;
import static node_express_api.utils.TestData.user2;
import static node_express_api.utils.TestData.user3;
import static node_express_api.utils.TestData.user4;

public class FunctionalTest extends BaseTest {
    @DataProvider(name = "addUsers")
    public Object[][] addUsers() {
        return new Object[][] {
                {new User[]{user1, user2, user3, user4}}
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

//  чистим за собой
//        getPage().getByRole(AriaRole.LINK,
//                new Page.GetByRoleOptions().setName(DELETE_MENU).setExact(true)).click();
//
//        getPage().getByLabel(LABEL_USER_ID).fill(user1.getId());
//        getPage().getByRole(AriaRole.BUTTON,
//                new Page.GetByRoleOptions().setName(BUTTON_DELETE).setExact(true)).click();
//        getPage().reload();
//        List<ElementHandle> listTr = getPage().querySelectorAll("tr");
//
//        assert(listTr.size() == 1);
    }

    @Test
    public void testSearchUsers() {
        ArrayList<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(ADD_MENU).setExact(true)).click();
        String id = "";
        for (User user : users) {
            getPage().getByLabel(LABEL_FIRST_NAME).fill(user.getFirstName());
            getPage().getByLabel(LABEL_LAST_NAME).fill(user.getLastName());
            getPage().getByLabel(LABEL_AGE).fill(user.getAge());
            getPage().getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName(BUTTON_ADD).setExact(true)).click();
            id = getPage().locator( "tbody>tr")
                    .last().locator("td")
                    .nth(3).innerText();
            user.setId(id);
        }




// Здесь будет основной тест





        assert(true);

//  чистим за собой
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(DELETE_MENU).setExact(true)).click();
        for (User user : users) {
            getPage().getByLabel(LABEL_USER_ID).fill(user.getId());
            getPage().getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName(BUTTON_DELETE).setExact(true)).click();
        }

        assert(true);
    }
}
