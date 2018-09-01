package selenium.stepDefinitions

import constants.MyConstants
import cucumber.api.scala.{EN, ScalaDsl}
import org.openqa.selenium.{By, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import selenium.world.SeleniumWorld
import java.util.concurrent.TimeUnit
import entities.User
import org.openqa.selenium.support.ui.Select
import selenium.objectDescriptors.ObjectDescriptors

class SeleniumStepDefinition extends ScalaDsl with EN {

  Given("""^I am surfing MyStore website$"""){ () =>
    System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe")
    val capabilities = DesiredCapabilities.chrome
    val options = new ChromeOptions
    options.addArguments("incognito")
    options.addArguments("start-maximized")
    capabilities.setCapability(ChromeOptions.CAPABILITY, options)
    SeleniumWorld.driver = Some(new ChromeDriver(capabilities))
    SeleniumWorld.driver.get.manage.timeouts.implicitlyWait(30, TimeUnit.SECONDS)
    SeleniumWorld.driver.get.navigate().to(MyConstants.myStoreURL)
  }

  When("""^I want to register$"""){ () =>
    SeleniumWorld.myUser = Some(User(s"selenium.test.${new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date())}@test.com", "MatteoBullo123!"))
    SeleniumWorld.driver.get.findElementByClassName(ObjectDescriptors.LoginAndLogout.loginButton).click()
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.emailCreate).sendKeys(SeleniumWorld.myUser.get.email)
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.submitCreate).click()

    assert(SeleniumWorld.driver.get.getCurrentUrl.contains("my-account"), "Something gone wrong! You were not redirected to the account creation page!")
  }

  Then("""^I fill the registration form$""") { () =>
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.gender).click()
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.firstName).sendKeys("Selenium")
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.lastName).sendKeys("Test")
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.password).sendKeys(SeleniumWorld.myUser.get.password)
    val day : WebElement = SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.day)
    new Select(day).selectByIndex(2)
    val month : WebElement = SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.month)
    new Select(month).selectByIndex(9)
    val year : WebElement = SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.year)
    new Select(year).selectByValue("1991")
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.addressFirstName).sendKeys("Selenium")
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.addressLastName).sendKeys("Test")
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.address).sendKeys("Via Test 32")
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.city).sendKeys("Caricchio")
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.CAP).sendKeys("12354")
    val state : WebElement = SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.state)
    new Select(state).selectByIndex(9)
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.mobile).sendKeys("132456789")
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.addressAlias).sendKeys("alias")
    SeleniumWorld.driver.get.findElementById(ObjectDescriptors.Registration.submitAccountButton).click()
  }

  Then("""^I can access myAccount area$"""){ () =>
    assert(SeleniumWorld.driver.get.findElement(By.xpath(ObjectDescriptors.Registration.accountArea)).isDisplayed , "Something gone wrong! You were not redirected to your account area!")
    println(s"\nAccount for user ${SeleniumWorld.myUser.get.email} correctly created!")
  }

  When("""^I logout$"""){ () =>
    SeleniumWorld.driver.get.findElementByClassName(ObjectDescriptors.LoginAndLogout.logoutButton).click()
  }

  Then("""^I am logged out$"""){ () =>
    assert(SeleniumWorld.driver.get.findElementByClassName(ObjectDescriptors.LoginAndLogout.loginButton).isDisplayed , "Something gone wrong! You were not redirected to the account creation page!")
    println("\nEverything gone fine, I am correctly logged out!")
  }
}
