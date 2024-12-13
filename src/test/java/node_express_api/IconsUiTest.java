package node_express_api;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import node_express_api.runner.BaseTest;
import node_express_api.utils.LoggerUtils;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static node_express_api.utils.TestData.*;

public class IconsUiTest extends BaseTest {

    @Test
    public void testIconsUi() {
        LoggerUtils.logSuccess("TestCase: testIconsUI");
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(ADD_MENU).setExact(true)).click();
        getPage().getByLabel(LABEL_FIRST_NAME).fill(user1.getFirstName());
        getPage().getByLabel(LABEL_LAST_NAME).fill(user1.getLastName());
        getPage().getByLabel(LABEL_AGE).fill(user1.getAge());
        getPage().getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(BUTTON_ADD).setExact(true)).click();
        String id = getPage().locator( "tbody>tr>td").nth(3).innerText();
        user1.setId(id);
        Locator tableRows = getPage().locator( "tbody>tr");

        assertThat(tableRows).hasCount(1);

        Locator userRow = getPage().locator( "table>tbody>tr");
        Locator userCells = userRow.getByRole(AriaRole.CELL);
        Locator editIconInUserRow = getPage().locator( "tbody>tr>td").nth(4)
                .locator("i>a>svg.bi-pen");
        Locator deleteIconInUserRow = getPage().locator( "tbody>tr>td").nth(5)
                .locator("i>a>svg.bi-trash");

        assertThat(userRow).hasCount(1);
        assertThat(userCells).hasCount(6);
        assertThat(editIconInUserRow).isVisible();
        assertThat(editIconInUserRow).hasCount(1);
        assertThat(deleteIconInUserRow).isVisible();
        assertThat(deleteIconInUserRow).hasCount(1);

        editIconInUserRow.hover( new Locator.HoverOptions().setForce(true));

        assertThat(editIconInUserRow).hasCSS("color", "rgb(0, 128, 0)");

        deleteIconInUserRow.hover( new Locator.HoverOptions().setForce(true));

        assertThat(deleteIconInUserRow).hasCSS("color", "rgb(255, 0, 0)");
    }
}