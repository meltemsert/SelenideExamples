package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyManager;

import static org.assertj.core.api.Assertions.assertThat;
import static com.codeborne.selenide.Selenide.$;

public class DemoQATests {
    PropertyManager propertyManager = new PropertyManager();

    public void open() {
        String url = propertyManager.getProperty("APP_URL");
        Selenide.open(url);
    }

    public String getUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    @BeforeMethod(alwaysRun = true)
    public void openDriver() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void clearCookies() {
        DriverFactory.clearCookies();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.close();
    }

    @Test
    public void clickButtonAndGetMessage() {

        // 1. Web sayfasını aç
        open();

        // 2. "Buttons" öğesine tıkla
        $("#item-4").click();

        // 3. "Click Me" düğmesine tıkla
        $(new By.ByXPath("//button[text() = 'Click Me']")).click();

        // 4. Görünen mesajı al ve yazdır
        SelenideElement messageElement = $("#dynamicClickMessage");
        String actualMessage = messageElement.getText();
        String expectedMessage = "You have done a dynamic click";
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


}
