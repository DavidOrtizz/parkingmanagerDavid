package com.hormigo.david.parkingmanager.user.presentation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hormigo.david.parkingmanager.user.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {
  @MockBean
  private UserService userService;
  @Value("${local.server.port}")
  private int port;
  private static ChromeDriver chromeDriver;

  @BeforeAll
  public static void prepareWebDriver() {

    System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    chromeDriver = new ChromeDriver(options);
  }

  @Test
  public void checkIndex() {
    String url = "http://localhost:" + port + "/index";
    chromeDriver.get(url);
    String titleBienvenido = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Bienvenidos al CPIFP Los Camaleones", titleBienvenido);

    // Comprobación de los botones del menu
    WebElement inicioButton = chromeDriver.findElement(By.id("to-home-link"));
    assertNotNull(inicioButton);
    WebElement usuariosButton = chromeDriver.findElement(By.id("to-users-link"));
    assertNotNull(usuariosButton);
    WebElement sorteoButton = chromeDriver.findElement(By.id("to-draws-link"));
    assertNotNull(sorteoButton);

    inicioButton.click();
    String titleInicio = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Inicio", titleInicio);
    usuariosButton.click();
    String titleUsuarios = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Usuarios", titleUsuarios);
    sorteoButton.click();
    String titleSorteo = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Sorteo", titleSorteo);

    chromeDriver.quit();
  }

  @Test
  public void checkUserList() {
    String url = "http://localhost:" + port + "users";
    chromeDriver.get(url);
    String titleUser = chromeDriver.getTitle();
    WebElement createButton = chromeDriver.findElement(By.id("users-button-create"));
    WebElement actualHeading = chromeDriver.findElement(By.id("users-title"));
    WebElement table = chromeDriver.findElement(By.id("table"));
    String actualHeadingText = actualHeading.getText();

    assertAll("Comprobar que la lista de usuarios se muestra",
    () -> {assertNotNull(createButton);},
    () -> {assertEquals("Usuarios", titleUser);},
    () -> {assertEquals("Usuarios", actualHeadingText);},
    () -> {assertNotNull(table);}
    );

    // Comprobación de los botones del menu
    WebElement inicioButton = chromeDriver.findElement(By.id("to-home-link"));
    assertNotNull(inicioButton);
    WebElement usuariosButton = chromeDriver.findElement(By.id("to-users-link"));
    assertNotNull(usuariosButton);
    WebElement sorteoButton = chromeDriver.findElement(By.id("to-draws-link"));
    assertNotNull(sorteoButton);

    inicioButton.click();
    String titleInicio = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Inicio", titleInicio);
    usuariosButton.click();
    String titleUsuarios = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Usuarios", titleUsuarios);
    sorteoButton.click();
    String titleSorteo = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Sorteo", titleSorteo);

    chromeDriver.quit();
  }

  @Test
  public void checkCreateformUser() {
    String url = "http://localhost:" + port + "user/createform";
    chromeDriver.get(url);
    String createTitle = chromeDriver.getTitle();
    assertEquals("Crear nuevo usuario", createTitle);
    // Correo
    WebElement correoLabel = chromeDriver.findElement(By.id("user-create-label-email"));
    assertNotNull(correoLabel);
    WebElement correoEmail = chromeDriver.findElement(By.id("user-create-field-email"));
    correoEmail.sendKeys("david@hotmail.com");
    // Nombre
    WebElement nombreLabel = chromeDriver.findElement(By.id("user-create-label-name"));
    assertNotNull(nombreLabel);
    WebElement nombreText = chromeDriver.findElement(By.id("user-create-field-name"));
    nombreText.sendKeys("David");
    // Primer apellido
    WebElement primerApellidoLabel = chromeDriver.findElement(By.id("user-create-label-lastname1"));
    assertNotNull(primerApellidoLabel);
    WebElement primerApellidoText = chromeDriver.findElement(By.id("user-create-field-lastname1"));
    primerApellidoText.sendKeys("Ortiz");
    // Segundo apellido
    WebElement segundoApellidoLabel = chromeDriver.findElement(By.id("user-create-label-lastname2"));
    assertNotNull(segundoApellidoLabel);
    WebElement segundoApellidoText = chromeDriver.findElement(By.id("user-create-field-lastname2"));
    segundoApellidoText.sendKeys("Corchero");
    // Rol
    WebElement rolLabel = chromeDriver.findElement(By.className("label"));
    assertNotNull(rolLabel);
    WebElement rolFiel = chromeDriver.findElement(By.id("user-create-field-role"));
    assertNotNull(rolFiel);
    // Boton de crear
    WebElement createButton = chromeDriver.findElement(By.id("user-create-button-submit"));
    createButton.click();
    assertNotNull(createButton);


    // Comprobación de los botones del menu
    WebElement inicioButton = chromeDriver.findElement(By.id("to-home-link"));
    assertNotNull(inicioButton);
    WebElement usuariosButton = chromeDriver.findElement(By.id("to-users-link"));
    assertNotNull(usuariosButton);
    WebElement sorteoButton = chromeDriver.findElement(By.id("to-draws-link"));
    assertNotNull(sorteoButton);
    
    inicioButton.click();
    String titleInicio = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Inicio", titleInicio);
    usuariosButton.click();
    String titleUsuarios = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Usuarios", titleUsuarios);
    sorteoButton.click();
    String titleSorteo = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Sorteo", titleSorteo);

    chromeDriver.quit();
  }

  @Test
  public void checkDraw() {
    String url = "http://localhost:" + port + "draw/list";
    chromeDriver.get(url);
    String titleDraw = chromeDriver.getTitle();
    assertEquals("Sorteos", titleDraw);
    WebElement createButton = chromeDriver.findElement(By.className("button is-primary"));
    createButton.click();
    assertNotNull(createButton);


    // Comprobación de los botones del menu
    WebElement inicioButton = chromeDriver.findElement(By.id("to-home-link"));
    assertNotNull(inicioButton);
    WebElement usuariosButton = chromeDriver.findElement(By.id("to-users-link"));
    assertNotNull(usuariosButton);
    WebElement sorteoButton = chromeDriver.findElement(By.id("to-draws-link"));
    assertNotNull(sorteoButton);
    
    inicioButton.click();
    String titleInicio = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Inicio", titleInicio);
    usuariosButton.click();
    String titleUsuarios = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Usuarios", titleUsuarios);
    sorteoButton.click();
    String titleSorteo = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Sorteo", titleSorteo);

    chromeDriver.quit();
  }

  @Test
  public void checkCreateformDraw() {
    String url = "http://localhost:" + port + "draw/createform";
    chromeDriver.get(url);
    WebElement descripcionLabel = chromeDriver.findElement(By.id("draw-label-description"));
    assertNotNull(descripcionLabel);
    WebElement descripcionText = chromeDriver.findElement(By.id("draw-field-description"));
    descripcionText.sendKeys("Descripcion del sorteo");
    WebElement crearDraw = chromeDriver.findElement(By.id("draw-button-submit"));
    crearDraw.click();
    assertNotNull(crearDraw);

    // Comprobación de los botones del menu
    WebElement inicioButton = chromeDriver.findElement(By.id("to-home-link"));
    assertNotNull(inicioButton);
    WebElement usuariosButton = chromeDriver.findElement(By.id("to-users-link"));
    assertNotNull(usuariosButton);
    WebElement sorteoButton = chromeDriver.findElement(By.id("to-draws-link"));
    assertNotNull(sorteoButton);
    
    inicioButton.click();
    String titleInicio = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Inicio", titleInicio);
    usuariosButton.click();
    String titleUsuarios = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Usuarios", titleUsuarios);
    sorteoButton.click();
    String titleSorteo = chromeDriver.getTitle(); // Comprueba el titulo
    assertEquals("Sorteo", titleSorteo);

    chromeDriver.quit();
  }
}
