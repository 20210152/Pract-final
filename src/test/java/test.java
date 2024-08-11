import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class test {

    public static void main(String[] args) {
        // Configura la ruta del driver de Edge (asegúrate de que sea la ruta correcta en tu sistema)
        System.setProperty("webdriver.edge.driver", "C:\\Users\\jacka\\Downloads\\edgedriver_win64\\msedgedriver.exe");

        // Configuración de EdgeOptions si es necesario
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");

        // Inicializa el WebDriver
        WebDriver driver = new EdgeDriver(options);

        // URL del sitio web de prueba
        String baseUrl = "https://practicetestautomation.com/practice-test-login/";

        // Datos de los usuarios (los primeros dos usuarios son 'student' con contraseña 'Password123')
        String[] usernames = {"student", "student", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10"};
        String password = "Password123";  // Contraseña para los primeros dos usuarios

        // Contraseña genérica para los otros usuarios
        String genericPassword = "your_password";  // Reemplaza con la contraseña correspondiente

        // Itera sobre los usuarios para realizar el login
        for (int i = 0; i < usernames.length; i++) {
            try {
                // Abre el sitio web
                driver.get(baseUrl);

                // Encuentra el campo de nombre de usuario y contraseña
                WebElement usernameField = driver.findElement(By.id("username"));
                WebElement passwordField = driver.findElement(By.id("password"));

                // Introduce las credenciales
                usernameField.sendKeys(usernames[i]);
                if (i < 2) {
                    passwordField.sendKeys(password);  // Usa 'Password123' para los primeros dos usuarios
                } else {
                    passwordField.sendKeys(genericPassword);  // Usa la contraseña genérica para los demás usuarios
                }

                // Encuentra y hace clic en el botón de login
                WebElement loginButton = driver.findElement(By.id("submit"));
                loginButton.click();

                // Espera unos segundos para simular la interacción y luego imprime el resultado
                Thread.sleep(2000);

                // Verifica si el login fue exitoso revisando la URL o algún elemento específico en la página de destino
                if (driver.getCurrentUrl().contains("success")) {
                    System.out.println("Login exitoso para el usuario: " + usernames[i]);
                } else {
                    System.out.println("Login fallido para el usuario: " + usernames[i]);
                }

                // Regresa a la página de login para el próximo usuario (opcional)
                driver.navigate().back();

            } catch (Exception e) {
                System.out.println("Ocurrió un error con el usuario: " + usernames[i]);
                e.printStackTrace();
            }
        }

        // Cierra el navegador
        driver.quit();
    }
}
