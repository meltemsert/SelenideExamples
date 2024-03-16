package tests;

import com.codeborne.selenide.Selenide;
import drivers.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyManager;

import static com.codeborne.selenide.Selenide.$;


public class DemoQAGenerateTests {
    PropertyManager propertyManager = new PropertyManager();

    public void open() {
        String url = propertyManager.getProperty("APP_URL_2");
        Selenide.open(url);
    }

    @BeforeMethod(alwaysRun = true)
    public void openDriver() {
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        DriverFactory.clearCookies();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.close();
    }

    @Test
    public void testAddAndEditRecordInWebTable() {

        // 1. Web sayfasını aç
        open();

        // "ADD" düğmesine tıkla
        $("#addNewRecordButton").click();

        // Yeni kaydı doldur
        $("#firstName").setValue("Meltem");
        $("#lastName").setValue("Sert");
        $("#userEmail").setValue("meltemsert29@gmail.com");
        $("#age").setValue("30");
        $("#salary").setValue("50000");
        $("#department").setValue("EGM");
        $("#submit").click();

        // Eklenen kaydın düzenleme düğmesini tıklayın (örnekte ilk satır düzenleniyor)
        $("#edit-record-4").click();

        // Yeni bilgileri gir
        $("#salary").setValue("55000");
        $("#submit").click();

    }
}
