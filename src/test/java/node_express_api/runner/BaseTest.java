package node_express_api.runner;

import com.microsoft.playwright.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.microsoft.playwright.options.AriaRole;
import node_express_api.utils.LoggerUtils;
import node_express_api.utils.BrowserManager;
import node_express_api.utils.ConfigProperties;
import node_express_api.utils.User;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static node_express_api.utils.TestData.*;

public abstract class BaseTest {

    private final Playwright playwright = Playwright.create();
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private APIRequestContext apiRequestContext;

    @BeforeSuite
    protected void checkIfPlaywrightCreatedAndBrowserLaunched() {
        if (playwright != null) {
            LoggerUtils.logInfo("Playwright created");
        } else {
            LoggerUtils.logFatal("FATAL. Playwright is NOT created.");
            System.exit(1);
        }
    }

    @BeforeClass
    protected void launchBrowser() {
        browser = BrowserManager.createBrowser(playwright, ConfigProperties.ENVIROMENT_CHROMIUM);

        if (browser.isConnected()) {
            LoggerUtils.logInfo("Browser " + browser.browserType().name() + " is connected.");
        } else {
            LoggerUtils.logFatal("FATAL: Browser is NOT connected.");
            System.exit(1); // выходим из системы с кодом ошибки 1
        }
        apiRequestContext = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(API_USERS_URL));
    }

    @BeforeMethod
    protected void createContextAndPage(Method method) {
        context = browser.newContext();
        LoggerUtils.logInfo("Context created.");

        page = context.newPage();
        LoggerUtils.logInfo("Page created.");

        getPage().navigate(BASE_URL);
        if (isOnHomePage()) {
            LoggerUtils.logInfo("Base url opened");
        } else {
            LoggerUtils.logError("ERROR: Base url was NOT opened.");
        }
        LoggerUtils.logSuccess("Start test");
        APIResponse response = apiRequestContext.delete("");
        int responseCode = response.status();

        Assert.assertEquals(responseCode, 200);
    }

    @AfterMethod
    protected void closeContext(Method method, ITestResult result) {
        if (page != null) {
            page.close();
            LoggerUtils.logInfo("Page closed.");
        }
        if (context != null) {
            context.close();
            LoggerUtils.logInfo("Context closed.");
        }
    }

    @AfterClass
    protected void closeBrowser() {
        if (browser != null && browser.isConnected()) {
            browser.close();
            if(!browser.isConnected()) {
                LoggerUtils.logInfo("Browser is closed");
            }
        }
    }

    @AfterSuite
    protected void closeBrowserAndPlaywright() {
        if (apiRequestContext != null) {
            apiRequestContext.dispose();
        }
        if (playwright != null) {
            playwright.close();
            LoggerUtils.logInfo("Playwright closed.");
        }
    }

    private boolean isOnHomePage() {
        getPage().waitForLoadState();
        return getPage().url().equals(BASE_URL + HOME_END_POINT)
                && !page.content().isEmpty();
    }

    protected Page getPage() {
        return page;
    }

    protected boolean getIsOnHomePage() {
        return isOnHomePage();
    }

    public void addUsers(ArrayList<User> users) {
        getPage().getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(ADD_MENU).setExact(true)).click();
        String id = "";
        for (User user : users) {
            getPage().getByLabel(LABEL_FIRST_NAME).fill(user.getFirstName());
            getPage().getByLabel(LABEL_LAST_NAME).fill(user.getLastName());
            getPage().getByLabel(LABEL_AGE).fill(user.getAge());
            getPage().getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName(BUTTON_ADD).setExact(true)).click();
            id = getPage().locator("tbody>tr")
                    .last().locator("td")
                    .nth(3).innerText();
            user.setId(id);
        }
    }
}
