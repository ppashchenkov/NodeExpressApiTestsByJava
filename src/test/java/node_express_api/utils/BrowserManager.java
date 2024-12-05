package node_express_api.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.util.Map;

public final class BrowserManager {

    public static Browser createBrowser(Playwright playwright, Map<String,String> enviroment) {
        String browserName = enviroment.get("browser");
        boolean isHeadLess = Boolean.parseBoolean(enviroment.get("isHeadLess"));
        int slowMo = Integer.parseInt(enviroment.get("slowMo"));

        switch (browserName) {
            case "chromium" -> {
                LoggerUtils.logInfo("INFO: " + browserName + " launched.");
                return playwright.chromium()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadLess)
                                .setSlowMo(slowMo));
            }
            case "firefox" -> {
                LoggerUtils.logInfo("INFO: " + browserName + " launched.");
                return playwright.firefox()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadLess)
                                .setSlowMo(slowMo));
            }
            case "webkit" -> {
                LoggerUtils.logInfo("INFO: " + browserName + " launched.");
                return playwright.webkit()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadLess)
                                .setSlowMo(slowMo));
            }
            default -> {
                LoggerUtils.logError("Error: " + browserName + " is NOT match any options. Cromium() launched.");
                return playwright.chromium()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(true)
                                .setSlowMo(0));
            }
        }
    }

    public static Browser createBrowser(Playwright playwright, String browser, String isHeadlessString, String slowMoString) {
        boolean isHeadless;
        int slowMo;

        if (isHeadlessString != null && !isHeadlessString.isEmpty() && slowMoString != null && !slowMoString.isEmpty()) {
            isHeadless = Boolean.parseBoolean(isHeadlessString);
            slowMo = Integer.parseInt(slowMoString);
        } else {
            isHeadless = true;
            slowMo = 0;
        }

        switch (browser) {
            case "firefox" -> {
                return playwright.firefox()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadless)
                                .setSlowMo(slowMo));
            }
            case "webkit" -> {
                return playwright.webkit()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadless)
                                .setSlowMo(slowMo));
            }
            default -> {
                return playwright.chromium()
                        .launch(new BrowserType.LaunchOptions()
                                .setHeadless(isHeadless)
                                .setSlowMo(slowMo));
            }
        }
    }
}
