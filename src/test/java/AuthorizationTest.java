import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Parameterized.class)
public class AuthorizationTest {
    private final String baseUrl = "http://automationpractice.com/index.php?";
    private final String username = "user66557788@gmail.com";
    private final String password = "55555";
    private final Browser browser;

    public AuthorizationTest(Browser browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Iterable browsers() {
        return Arrays.asList(
                new Object[][]{
                        {Browser.CHROME},
                        {Browser.FIREFOX}
                }
        );
    }

    @Test
    @DisplayName("Успешная авторизация зарегистрированным пользователем")
    public void when_authorizeByValidUser_expect_userLoggedIn() {
        try (var baseTest = new BaseTest(browser)) {
            var driver = baseTest.getDriver();
            var page = new AuthorizationPage(driver);
            driver.get(baseUrl);
            page.login(username, password);

            assertTrue(page.isLogoutBtnDisplayed(), "User doesn't see the logout button");
        }
    }

    @Test
    @DisplayName("Разлогинится пользователем с помощью кнопки 'Logout'")
    public void given_authorizedUser_when_logOutByButton_expect_userIsUnauthorized() {
        try (var baseTest = new BaseTest(browser)) {
            var driver = baseTest.getDriver();
            var page = new AuthorizationPage(driver);
            authorizeByUser(driver, page);
            page.logout();

            assertTrue(page.isLoginBtnDisplayed(), "User doesn't see the login button");
        }
    }

    @Test
    @DisplayName("Разлогинится с помощью перхода пользователя по ссылке '{baseUrl}/account/logout/'")
    public void given_authorizedUser_when_logOutByLink_expect_userIsUnauthorized() {
        try (var baseTest = new BaseTest(browser)) {
            var logoutUrl = "mylogout=";
            var driver = baseTest.getDriver();
            var page = new AuthorizationPage(driver);
            authorizeByUser(driver, page);
            driver.get(baseUrl + logoutUrl);

            assertTrue(page.isLoginBtnDisplayed(), "User doesn't see the login button");
        }
    }

    private void authorizeByUser(WebDriver driver, AuthorizationPage page) {
        driver.get(baseUrl);
        page.login(username, password);

        assertTrue(page.isLogoutBtnDisplayed(), "Authorization before test failed");
    }
}
