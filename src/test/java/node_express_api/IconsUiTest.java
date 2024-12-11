package node_express_api;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import node_express_api.runner.BaseTest;
import node_express_api.utils.LoggerUtils;
import node_express_api.utils.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static node_express_api.utils.TestData.*;

public class IconsUiTest extends BaseTest {

    @Test
    public void testIconsUi() {
        List<Locator> tableRows = new ArrayList<Locator>();

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

        tableRows = getPage().locator( "tbody>tr").all();

//        LoggerUtils.logInfo("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        LoggerUtils.logInfo(tableRows.toString());

        Assert.assertEquals(tableRows.size(), 1);

    }





}
