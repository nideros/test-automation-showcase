package selenium.stepDefinitions

import constants.MyConstants
import cucumber.api.scala.{EN, ScalaDsl}
import org.openqa.selenium.{By, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import selenium.world.SeleniumWorld
import java.util.concurrent.TimeUnit
import selenium.objectDescriptors.ObjectRepository

class BattleNetSteps extends ScalaDsl with EN {

  Given("""^I ama surfing Battle\.net website$"""){ () =>
    System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe")
    val capabilities = DesiredCapabilities.chrome
    val options = new ChromeOptions
    options.addArguments("incognito")
    options.addArguments("start-maximized")
    capabilities.setCapability(ChromeOptions.CAPABILITY, options)
    SeleniumWorld.driver = Some(new ChromeDriver(capabilities))
    SeleniumWorld.driver.get.manage.timeouts.implicitlyWait(30, TimeUnit.SECONDS)
    SeleniumWorld.driver.get.navigate().to(MyConstants.battleNetBaseURL)
  }

  When("""^I wanta to register$"""){ () =>
    SeleniumWorld.driver.get.findElement(By.xpath("//a[contains(@data-target, 'Navbar-accountDropdown')]")).click()
    SeleniumWorld.driver.get.findElement(By.xpath("//a[contains(@href, 'https://eu.battle.net/account/creation')]")).click()

    assert(SeleniumWorld.driver.get.getCurrentUrl.contains("eu.battle.net/account/creation"), "Something gone wrong! You were not redirected to the account creation page!")
  }

  Then("""^I filla the registration form$""") { () =>
    SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.firstname).sendKeys("Selenium")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.lastName).sendKeys("Test")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.dayOfBirth).sendKeys("6")
    val month : WebElement = SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.monthOfBirth)
    month.click()
    month.findElement(By.xpath(ObjectRepository.BattleNetRegistrationDescriptors.monthSelection)).click()
    SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.yearOfBirth).sendKeys("1991")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.email).sendKeys(s"selenium.test.${new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date())}@test.com")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.password).sendKeys("MatteoBullo123!")
    val question : WebElement = SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.selectSecretQuestion)
    question.click()
    question.findElement(By.xpath(ObjectRepository.BattleNetRegistrationDescriptors.secretQuestionValue)).click()
    SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.secretAnswer).sendKeys("Selenium answer")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.agreePrivacy).click()
    SeleniumWorld.driver.get.findElementById(ObjectRepository.BattleNetRegistrationDescriptors.createButton).click()
  }

  Then("""^I cana access myAccount area$"""){ () =>
    assert(SeleniumWorld.driver.get.getCurrentUrl.contains("eu.battle.net/account/"), "Something gone wrong! You were not redirected to the account creation page!")
  }

  When("""^I logouta$"""){ () =>

  }

  Then("""^I am loggeda out$"""){ () =>

  }
}
