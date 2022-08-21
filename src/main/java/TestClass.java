import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestClass extends BaseTestClass {

    @Test
    public void toDoItemPage() throws InterruptedException {
        driver.navigate().to("https://todomvc.com/examples/vue/");
        WebElement toDoField = driver.findElement(By.xpath("//input[@autofocus='autofocus']"));
        toDoField.click();
        toDoField.sendKeys("Clean the house");
        Thread.sleep(3000);
        toDoField.sendKeys(Keys.ENTER);
        driver.findElement(By.className("toggle")).click();
        String doneItem = driver.findElement(By.xpath("//li[@class='todo completed']")).getAttribute("class");
        Assert.assertEquals("todo completed", doneItem);

        var stop = 0;
    }

    @Test
    public void switchAlerts() {
        driver.navigate().to("https://demo.automationtesting.in");
        WebElement skipSignUn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn2")));
        skipSignUn.click();
        WebElement switchTo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='SwitchTo.html']")));
        switchTo.click();
        WebElement alerts = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='Alerts.html']")));
        alerts.click();
        WebElement alertsWithTexBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='#Textbox']")));
        alertsWithTexBox.click();
        driver.findElement(By.xpath("//button[@onclick='promptbox()']")).click();
        Assert.assertEquals(driver.switchTo().alert().getText(),"Please enter your name");

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Radostina");
        alert.accept();

        WebElement congrats = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("demo1")));
        Assert.assertEquals(congrats.getText(),"Hello Radostina How are you today");

        var stop = 0;
    }


    @Test
    public void switchIFrames() {
        driver.navigate().to("https://demo.automationtesting.in");
        WebElement skipSignUn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn2")));
        skipSignUn.click();
        WebElement switchTo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='SwitchTo.html']")));
        switchTo.click();
        WebElement frames = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='Frames.html']")));
        frames.click();
        driver.switchTo().frame(0);
        WebElement typingField = driver.findElement(By.xpath("//input[@type='text']"));
        typingField.sendKeys("Test IFrames");
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@href='Index.html']")).click();

        WebElement signIn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn1")));
        Assert.assertTrue(signIn.isDisplayed());

    }
}
