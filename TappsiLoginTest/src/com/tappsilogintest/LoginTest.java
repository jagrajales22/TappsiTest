

package com.tappsilogintest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Esta clase implementa varias pruebas para hacer login en la aplicación Tappsi. Los escenarios de prueba son los siguientes:
 * 1- Configuración de la aplicación y carga
 * 2- Login fallido con password incorrecto
 * 3- Login fallido con correo incorrecto
 * 4- Login fallido con clave en blanco
 * 5- Login fallido con correo en blanco
 * 6- Login exitoso
 * 7- Logout exitoso
 * @author jagrajales22
 *
 */
public class LoginTest {
	
	//Instancia de driver
	AppiumDriver<WebElement> driver;
	//Instancia de botón para pruebas
	private WebElement btn_create;
	//Instancia de indicador de tamaño de contraseñas (Hint text)
	int hintTextindicator;
		
	
	/**
	 * En el método Setup() se  inicializan las capabilities requeridas por Appium para realizar la prueba. En este caso, se trabajará con
	 * el emulador de Android Studio, creando un dispositivo Nexus con Android 6.0 Marshmallow. Es recomendable en futuras ocaciones, 
	 * remplazar este emulador por el  emulador de Android para Visual Studio, el cual ha probado ser más rápido. 
	 * @throws MalformedURLException
	 */
	@BeforeClass
		public void Setup() throws MalformedURLException{
		//Configuración de capabilities
		
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.4.16.1");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.APP, "C:\\Users\\OS\\Documents\\Java\\apps\\com.tappsi.passenger.android_2.5.9.apk");
		cap.setCapability("avd", "Nexus1");
		
		//Configuración de Driver Android
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		}
		
	/**
	 * ConfigTest() prueba las solicitudes de permisos para correr la aplicación y añade una espera hasta que cargue la ventana de login.
	 * Principalmente prueba que los elementos de la interfaz para solicitar permisos no sean nulos.
	 * @throws InterruptedException
	 */
	@Test(priority=1)
		public void ConfigTest() throws InterruptedException {
		
		//Permite uso de GPS para posición
		
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_allow_button")));		
		
		WebElement GPS_Allow = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		assertNotNull(GPS_Allow);
		assertEquals(GPS_Allow.getText(), "Allow");
		GPS_Allow.click();
		
		//Permite realizar llamadas desde la aplicación
		
		WebElement Call_Allow = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		assertNotNull(Call_Allow);
		assertEquals(Call_Allow.getText(), "Allow");
		Call_Allow.click();
		
		//Añade una espera hasta que la ventana de login esté cargada
		Thread.sleep(5000);
		wait = new WebDriverWait(driver, 60);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.tappsi.passenger.android:id/btn_login")));		
		}
	
	/**
	 * WrongPasswordLoginTest() prueba un login fallido con contraseña incorrecta. Prueba que los elementos no son nulos y que siguen en
	 * la ventana de login.
	 * @throws InterruptedException
	 */
	@Test(priority=2)
		public void WrongPasswordLoginTest() throws InterruptedException {
	
		WebElement txtEmail = driver.findElement(By.id("com.tappsi.passenger.android:id/txtEmail"));
		assertNotNull(txtEmail);
		txtEmail.sendKeys("test@test.org");
	
		WebElement txtPassword = driver.findElement(By.id("com.tappsi.passenger.android:id/txtPassword"));
		assertNotNull(txtPassword);
		String pass="WrongPassword";
		txtPassword.sendKeys(pass);
		hintTextindicator=pass.length();
	
		WebElement btn_login = driver.findElement(By.id("com.tappsi.passenger.android:id/btn_login"));
		assertNotNull(btn_login);
		btn_login.click();
	
		try{
			btn_create = driver.findElement (By.id("com.tappsi.passenger.android:id/btn_create_account"));
			}catch (Exception e){
				}
		assertNotNull(btn_create);
		}
	
	/**
	 * WrongEmailLoginTest() prueba un login fallido con un correo incorrecto. Prueba que los elementos no son nulos y que siguen en
	 * la ventana de login.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority=3)
		public void WrongEmailLoginTest() throws InterruptedException, IOException {

		WebElement txtEmail = driver.findElement(By.id("com.tappsi.passenger.android:id/txtEmail"));
		assertNotNull(txtEmail);
		txtEmail.sendKeys("wrong@test.org");

		WebElement txtPassword = driver.findElement(By.id("com.tappsi.passenger.android:id/txtPassword"));
		assertNotNull(txtPassword);
		txtPassword.click();
		clearText(txtPassword,hintTextindicator);
		String pass = "123Test";
		txtPassword.sendKeys(pass);
		hintTextindicator=pass.length();

		WebElement btn_login = driver.findElement(By.id("com.tappsi.passenger.android:id/btn_login"));
		assertNotNull(btn_login);
		btn_login.click();

		try{
			btn_create = driver.findElement (By.id("com.tappsi.passenger.android:id/btn_create_account"));
			}catch (Exception e){
				}
		assertNotNull(btn_create);
		
		WebElement btn_close = driver.findElement (By.id("android:id/button1"));
		assertNotNull(btn_close);
		btn_close.click();
		}
	
		
		
	
	/**
	 * BlankPasswordLoginTest() prueba un login fallido con contraseña en blanco. Prueba que los elementos no son nulos y que siguen en
	 * la ventana de login.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority=4)
		public void BlankPasswordLoginTest() throws InterruptedException, IOException {

		WebElement txtEmail = driver.findElement(By.id("com.tappsi.passenger.android:id/txtEmail"));
		assertNotNull(txtEmail);
		txtEmail.sendKeys("test@test.org");

		WebElement txtPassword = driver.findElement(By.id("com.tappsi.passenger.android:id/txtPassword"));
		txtPassword.click();
		clearText(txtPassword,hintTextindicator);
		String pass = " ";
		txtPassword.sendKeys(pass);
		hintTextindicator=pass.length();

		WebElement btn_login = driver.findElement(By.id("com.tappsi.passenger.android:id/btn_login"));
		assertNotNull(btn_login);
		btn_login.click();

		try{
			btn_create = driver.findElement (By.id("com.tappsi.passenger.android:id/btn_create_account"));
			}catch (Exception e){
				}
		assertNotNull(btn_create);
		}

	/**
	 * BlankEmailLoginTest() prueba un login fallido con un correo en blanco. Prueba que los elementos no son nulos y que siguen en
	 * la ventana de login.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority=5)
		public void BlankEmailLoginTest() throws InterruptedException, IOException {

		WebElement txtEmail = driver.findElement(By.id("com.tappsi.passenger.android:id/txtEmail"));
		assertNotNull(txtEmail);
		txtEmail.sendKeys(" ");

		WebElement txtPassword = driver.findElement(By.id("com.tappsi.passenger.android:id/txtPassword"));
		assertNotNull(txtPassword);
		txtPassword.click();
		clearText(txtPassword,hintTextindicator);
		String pass = "123Test";
		txtPassword.sendKeys(pass);
		hintTextindicator=pass.length();

		WebElement btn_login = driver.findElement(By.id("com.tappsi.passenger.android:id/btn_login"));
		assertNotNull(btn_login);
		btn_login.click();

		try{
			btn_create = driver.findElement (By.id("com.tappsi.passenger.android:id/btn_create_account"));
			}catch (Exception e){
				}
		assertNotNull(btn_create);
		}
	
	/**
	 * SuccessfulLoginTest() prueba un login exitoso con un correo y contraseña anteriormente registrado. Prueba que los elementos no sean
	 * nulos y luego prueba que se salga de la ventana de login preguntando por el botón del menú btn_menu.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority=6)
		public void SuccessfulLoginTest() throws InterruptedException, IOException {
		
		WebElement txtEmail = driver.findElement(By.id("com.tappsi.passenger.android:id/txtEmail"));
		assertNotNull(txtEmail);
		txtEmail.sendKeys("test@test.org");
		
		WebElement txtPassword = driver.findElement(By.id("com.tappsi.passenger.android:id/txtPassword"));
		assertNotNull(txtPassword);
		txtPassword.click();
		clearText(txtPassword,hintTextindicator);
		String pass = "123Test";
		txtPassword.sendKeys(pass);
		hintTextindicator=pass.length();
		
		
		WebElement btn_login = driver.findElement(By.id("com.tappsi.passenger.android:id/btn_login"));
		assertNotNull(btn_login);
		btn_login.click();
		
		//Añade una espera hasta que se sale exitosamente de la ventana de login
		WebDriverWait wait = new WebDriverWait(driver, 60); 
				
		wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.ImageButton")));
		WebElement btn_menu=null;
		 try{
			 btn_menu = driver.findElementByClassName("android.widget.ImageButton");
		 }catch (Exception e){
			}
		assertNotNull(btn_menu);		
		}
	
	/**
	 * SuccessfulLogoutTest() prueba un logout exitoso, accediendo a al perfil. La prueba consiste en validar que los elementos no son nulos
	 * y después de salir existe el botón btn_create. Esto queire decir que ya volvió a abrirse la ventana de login.
	 */
	@Test(priority=7)
		public void SuccessfulLogoutTest(){
		
		
		WebElement btn_menu = driver.findElementByClassName("android.widget.ImageButton");
		assertNotNull(btn_menu);
		btn_menu.click();
		
		
		WebElement btn_profile = driver.findElement(By.name("Profile"));
		assertNotNull(btn_profile);
		btn_profile.click();
		
		
		WebElement btn_logout = driver.findElement(By.id("com.tappsi.passenger.android:id/btn_logout"));
		assertNotNull(btn_logout);
		btn_logout.click();
		
		WebElement btn_ok = driver.findElement(By.id("android:id/button1"));
		assertNotNull(btn_ok);
		btn_ok.click();
		
		try{
			btn_create = driver.findElement (By.id("com.tappsi.passenger.android:id/btn_create_account"));
			}catch (Exception e){
				}
		assertNotNull(btn_create);
		
		}
	
	
	
		/**
		 * El método clearText(WebElement, int), fue propuesto para poder borrar en los campos de hint text (password), dado que el método
		 * clear() falla al tratar de borrar el texto. De acuerdo a la literatura, en otras versiones de Appium, existen funciones que
		 * podrían facilitar este trabajo como sendKeyEvent(int), setText(String) y pressKeyCode(int). En este método se realizar borrado 
		 * emulando la interacción de un usuario tocando la pantalla. 
		 * @param web
		 * @param indicator
		 * @throws IOException
		 */
		public void clearText(WebElement web, int indicator) throws IOException{
			
			TouchAction action = new TouchAction(driver);
			action.longPress(web).perform();
			driver.tap(1, 545, 403, 1);
			
			for(int i=0;i<indicator;i++){
				driver.tap(1, 718, 998, 1);
			}
		
		}
		
	/**
	 * closeBrowser() cierra el driver para liberar recursos.
	 */
	@AfterClass
		public void closeBrowser(){
		 driver.quit();
	    }
		
}
