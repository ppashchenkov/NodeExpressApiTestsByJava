package node_express_api;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import node_express_api.runner.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static node_express_api.utils.TestData.*;

public class NavigationTest extends BaseTest {
    @DataProvider(name = "navBarMenu")
    public Object[][] menuItems() {
        return new Object[][] {
            {ADD_MENU, ADD_END_POINT, ADD_TITLE, H2_ADD_TITLE},
            {SEARCH_MENU, SEARCH_END_POINT, SEARCH_TITLE, H2_SEARCH_TITLE},
            {EDIT_MENU, EDIT_END_POINT, EDIT_TITLE, H2_EDIT_TITLE},
            {DELETE_MENU, DELETE_END_POINT, DELETE_TITLE, H2_DELETE_TITLE}
        };
    }

    @Test
    public void testBaseUrlLanding() {
        getPage().navigate(BASE_URL);

        assertThat(getPage()).hasURL(BASE_URL + HOME_END_POINT);
        assertThat(getPage()).hasTitle(ADD_TITLE);
        assertThat(getPage().locator("h1")).containsText(H1_CONTENT);
    }

    @Test(dataProvider = "navBarMenu")
    public void testMenuItems(String menu, String endPoint, String title, String h2Title) {
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(menu).setExact(true)).click();

        assertThat(getPage()).hasURL(BASE_URL + HOME_END_POINT + endPoint);
        assertThat(getPage().locator("h1")).containsText(H1_CONTENT);
        assertThat(getPage()).hasTitle(title);
        assertThat(getPage().locator("h2").first()).containsText(h2Title);
    }
}