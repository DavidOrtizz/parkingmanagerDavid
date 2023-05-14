package com.hormigo.david.parkingmanager.bdd.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hormigo.david.parkingmanager.user.service.UserService;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class showCreateDrawSteps {
  @MockBean
  private UserService userService;
  @Value("${local.server.port}")
  private int port;
  private static ChromeDriver driver;

  @BeforeAll
  public static void prepareWebDriver() {
    System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver(options);
  }

  @Given("un usuario que esta en la pagina inicial")
  public void indexSorteo() {
    driver.get("http://localhost:" + port + "/");
  }

  @When("el usuario hace click sobre el botón de Sorteo")
  public void clickListaSorteo() {
    driver.findElement(By.id("to-draws-link")).click();
  }

  @Then("se muestran todos los sorteos del sistema")
  public void mostrarListaSorteo() {
    String currentUrl = driver.getCurrentUrl();
    assertTrue(currentUrl.contains("/draws"));
  }
}
