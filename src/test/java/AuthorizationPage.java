import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;

public class AuthorizationPage {
    private By loginBtn = className("login");
    private By loginField = id("email");
    private By passwordField = id("passwd");
    private By submitBtn = id("SubmitLogin");
    private By logoutBtn = className("logout");
    WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(loginBtn).click();
        driver.findElement(loginField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitBtn).click();
    }

    public boolean isLogoutBtnDisplayed() {
        return driver.findElement(logoutBtn).isDisplayed();
    }

    public boolean isLoginBtnDisplayed() {
        return driver.findElement(loginBtn).isDisplayed();
    }

    public void logout() {
        driver.findElement(logoutBtn).click();
    }
}
