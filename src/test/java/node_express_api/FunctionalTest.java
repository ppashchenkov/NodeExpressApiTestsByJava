package node_express_api;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import node_express_api.runner.BaseTest;
import node_express_api.utils.LoggerUtils;
import node_express_api.utils.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.xpath.XPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static node_express_api.utils.TestData.*;
import static node_express_api.utils.TestData.user1;
import static node_express_api.utils.TestData.user2;
import static node_express_api.utils.TestData.user3;
import static node_express_api.utils.TestData.user4;
import static node_express_api.utils.User.*;

public class FunctionalTest extends BaseTest {
    @DataProvider(name = "addUsers")
    public Object[][] addUsers() {
        return new Object[][] {
                {new User[]{user1, user2, user3, user4}}
//                {new User[]{user2}},
//                {new User[]{user3}},
//                {new User[]{user4}},
        };
    }

    @Test
    public void testAddUser() {
        ArrayList<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(ADD_MENU).setExact(true)).click();
        for (User user: users) {
            getPage().getByLabel(LABEL_FIRST_NAME).fill(user.getFirstName());
            getPage().getByLabel(LABEL_LAST_NAME).fill(user.getLastName());
            getPage().getByLabel(LABEL_AGE).fill(user.getAge());
            getPage().getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName(BUTTON_ADD).setExact(true)).click();
            String id = getPage().locator( "xpath = //*[@id='usersList']/tr/td").last().innerText();
            LoggerUtils.logInfo(id);
            user.setId(id);
        }


        List<ElementHandle> listTr = new ArrayList<>();
        listTr = getPage().querySelectorAll("tr");
        LoggerUtils.logInfo("LIST TR +++++++++++++++++++++++++++++++++++++");
        LoggerUtils.logInfo(listTr.toString());
        System.out.println(listTr.size());

        assert(!listTr.isEmpty());

//        List<String> userData = new ArrayList<>();
//        for (int i = 1; i < listTr.size(); i++) {
//
//            for (int j = 0; j < 4; j++) {
//                ElementHandle el = listTr.get(i);
//                i++;
//                userData.add(el.innerText());
//                System.out.println("================================");
//                System.out.println(userData.get(j));
//            }
//        }
//        String actualFirstName = userData.get(0);
//        String actualLastName = userData.get(1);
//        String actualAge = userData.get(2);
//        String actualId = userData.get(3);

//        assert(actualFirstName.equals(user.getFirstName()));
//        assert(actualLastName.equals(user.getLastName()));
//        assert(actualAge.equals(user.getAge()));
//        assert(actualId.length() == 36);

        // Чистим за собой - удаляем всех users
//        getPage().getByRole(AriaRole.LINK,
//                new Page.GetByRoleOptions().setName(DELETE_MENU).setExact(true)).click();
//        for (int k = 0; k < userData.size(); k++) {
//            if (k != 0 && k % 3 == 0) {
//                getPage().getByLabel(LABEL_USER_ID).fill(userData.get(k));
//                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//                System.out.println(userData.get(k));
//                getPage().getByRole(AriaRole.BUTTON,
//                        new Page.GetByRoleOptions().setName(BUTTON_DELETE).setExact(true)).click();
//            }
//        }
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(DELETE_MENU).setExact(true)).click();
        for (User user: users) {
            getPage().getByLabel(LABEL_USER_ID).fill(user.getId());
            getPage().getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName(BUTTON_DELETE).setExact(true)).click();
        }

        getPage().reload();
        listTr = getPage().querySelectorAll("tr");
        LoggerUtils.logInfo("LIST TR #####################################");
        LoggerUtils.logInfo(listTr.toString());
        System.out.println(listTr.size());

//        assert(listTr.isEmpty());
    }
}
