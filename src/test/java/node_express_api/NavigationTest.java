package node_express_api;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import node_express_api.runner.BaseTest;
import node_express_api.utils.TestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static node_express_api.utils.TestData.*;

public class NavigationTest extends BaseTest {
    @DataProvider(name = "navBarMenu")
    public Object[][] menuItems() {
        return new Object[][] {
            {ADD_MENU, ADD_END_POINT},
            {SEARCH_MENU, SEARCH_END_POINT},
            {EDIT_MENU, EDIT_END_POINT},
            {DELETE_MENU, DELETE_END_POINT}
        };
    }

    @Test
    public void testBaseUrlLanding() {
//        getPage().navigate(BASE_URL);

        assertThat(getPage()).hasURL(BASE_URL + HOME_END_POINT);
        assertThat(getPage()).hasTitle(ADD_TITLE);
        assertThat(getPage().locator("h1")).containsText(H1_CONTENT);
    }

    @Test(dataProvider = "navBarMenu")
    public void testMenu(String menu, String endPoint) {
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(menu).setExact(true)).click();

        assertThat(getPage()).hasURL(BASE_URL + HOME_END_POINT + endPoint);
    }
}