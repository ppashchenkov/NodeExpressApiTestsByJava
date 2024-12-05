package node_express_api;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.sun.tools.jconsole.JConsoleContext;
import org.testng.Assert;
import node_express_api.runner.BaseTest;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static node_express_api.utils.TestData.*;

public class NavigationTest extends BaseTest {

    @Test
    public void testBaseUrlLanding() {
//        getPage().navigate(BASE_URL);

        assertThat(getPage()).hasURL(BASE_URL + HOME_END_POINT);
        assertThat(getPage()).hasTitle(ADD_TITLE);
        assertThat(getPage().locator("h1")).containsText(H1_CONTENT);
    }

    @Test
    public void testMenu() {
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(ADD_MENU).setExact(true)).click();

        assertThat(getPage()).hasURL(BASE_URL + HOME_END_POINT + ADD_END_POINT);
    }
}