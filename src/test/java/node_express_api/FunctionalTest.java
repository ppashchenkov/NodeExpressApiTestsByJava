package node_express_api;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import node_express_api.runner.BaseTest;
import node_express_api.utils.LoggerUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static node_express_api.utils.TestData.*;

public class FunctionalTest extends BaseTest {

    @Test
    public void testAddUser() {
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(ADD_MENU).setExact(true)).click();
        getPage().getByLabel(LABEL_FIRST_NAME).fill(FIRST_NAME);
        getPage().getByLabel(LABEL_LAST_NAME).fill(LAST_NAME);
        getPage().getByLabel(LABEL_AGE).fill(AGE);
        getPage().getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(BUTTON_ADD).setExact(true)).click();

        List<ElementHandle> listTr = new ArrayList<>();
        listTr = getPage().querySelectorAll("td");
        LoggerUtils.logInfo("LIST TR +++++++++++++++++++++++++++++++++++++");
        LoggerUtils.logInfo(listTr.toString());
        System.out.println(listTr.size());

        assert(!listTr.isEmpty());

        String actualFirstName = "";
        String actualLastName = "";
        String actualAge = "";
        String actualId = "";
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
        LoggerUtils.logInfo("LIST TR +++++++++++++++++++++++++++++++++++++");
        LoggerUtils.logInfo(listTr.toString());
        System.out.println(listTr.size());

        assert(listTr.isEmpty());
    }
}
