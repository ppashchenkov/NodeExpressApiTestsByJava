package node_express_api;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import node_express_api.runner.BaseTest;
import node_express_api.utils.LoggerUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static node_express_api.utils.TestData.*;
import static node_express_api.utils.UserData.*;

public class FunctionalTest extends BaseTest {

    @Test
    public void testAddUser() {
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(ADD_MENU).setExact(true)).click();
        getPage().getByLabel(LABEL_FIRST_NAME).fill(User1.firstName);
        getPage().getByLabel(LABEL_LAST_NAME).fill(User1.lastName);
        getPage().getByLabel(LABEL_AGE).fill(User1.age);
        getPage().getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(BUTTON_ADD).setExact(true)).click();

        List<ElementHandle> listTr = new ArrayList<>();
        listTr = getPage().querySelectorAll("td");
        LoggerUtils.logInfo("LIST TR +++++++++++++++++++++++++++++++++++++");
        LoggerUtils.logInfo(listTr.toString());
        System.out.println(listTr.size());

        assert(!listTr.isEmpty());

        List<String> userData = new ArrayList<>();
        for (int i = 0; i < listTr.size();) {
            for (int j = 0; j < 4; j++) {
                ElementHandle el = listTr.get(i);
                i++;
                userData.add(el.innerText());
                System.out.println("================================");
                System.out.println(userData.get(j));
            }
        }
        String actualFirstName = userData.get(0);
        String actualLastName = userData.get(1);
        String actualAge = userData.get(2);
        String actualId = userData.get(3);

        assert(actualFirstName.equals(User1.firstName));
        assert(actualLastName.equals(User1.lastName));
        assert(actualAge.equals(User1.age));
        assert(actualId.length() == 36);

        // Чистим за собой - удаляем всех users
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(DELETE_MENU).setExact(true)).click();
        for (int k = 0; k < userData.size(); k++) {
            if (k != 0 && k % 3 == 0) {
                getPage().getByLabel(LABEL_USER_ID).fill(userData.get(k));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(userData.get(k));
                getPage().getByRole(AriaRole.BUTTON,
                        new Page.GetByRoleOptions().setName(BUTTON_DELETE).setExact(true)).click();
            }
        }
        getPage().reload();
        listTr = getPage().querySelectorAll("td");
        LoggerUtils.logInfo("LIST TR #####################################");
        LoggerUtils.logInfo(listTr.toString());
        System.out.println(listTr.size());

        assert(listTr.isEmpty());
    }
}
