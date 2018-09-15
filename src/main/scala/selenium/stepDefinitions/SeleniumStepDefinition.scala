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
import selenium.objectDescriptors.ObjectRepository

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
    SeleniumWorld.driver.get.findElementByClassName(ObjectRepository.LoginAndLogout.loginButton).click()
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.emailCreate).sendKeys(SeleniumWorld.myUser.get.email)
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.submitCreate).click()

    assert(SeleniumWorld.driver.get.getCurrentUrl.contains("my-account"), "Something gone wrong! You were not redirected to the account creation page!")
  }

  Then("""^I fill the registration form$""") { () =>
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.gender).click()
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.firstName).sendKeys("Selenium")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.lastName).sendKeys("Test")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.password).sendKeys(SeleniumWorld.myUser.get.password)
    val day : WebElement = SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.day)
    new Select(day).selectByIndex(2)
    val month : WebElement = SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.month)
    new Select(month).selectByIndex(9)
    val year : WebElement = SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.year)
    new Select(year).selectByValue("1991")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.addressFirstName).sendKeys("Selenium")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.addressLastName).sendKeys("Test")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.address).sendKeys("Via Test 32")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.city).sendKeys("Caricchio")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.CAP).sendKeys("12354")
    val state : WebElement = SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.state)
    new Select(state).selectByIndex(9)
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.mobile).sendKeys("132456789")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.addressAlias).sendKeys("alias")
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.submitAccountButton).click()
  }

  Then("""^I can access myAccount area$"""){ () =>
    assert(SeleniumWorld.driver.get.findElement(By.xpath(ObjectRepository.Registration.accountArea)).isDisplayed , "Something gone wrong! You were not redirected to your account area!")
    println(s"\nAccount for user ${SeleniumWorld.myUser.get.email} correctly created!")
  }

  When("""^I logout$"""){ () =>
    SeleniumWorld.driver.get.findElementByClassName(ObjectRepository.LoginAndLogout.logoutButton).click()
  }

  Then("""^I am logged out$"""){ () =>
    assert(SeleniumWorld.driver.get.findElementByClassName(ObjectRepository.LoginAndLogout.loginButton).isDisplayed , "Something gone wrong! You were not redirected to the account creation page!")
    println("\nEverything gone fine, I am correctly logged out!")
  }

  When("""^I surf on T-SHIRTS category$"""){ () =>
    SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.tshirtsCategory).click()
  }

  Then("""^I add an item to cart$"""){ () =>
    import org.openqa.selenium.interactions.Actions
    val product = SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.firstProductContainer)
    val builder = new Actions(SeleniumWorld.driver.get)
    builder.moveToElement(product).build.perform()
    SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.addToCart).click()
  }

  Then("""^I want to proceed to checkout$"""){ () =>
    Thread.sleep(5000)
    SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.proceedToCheckout).click()
    Thread.sleep(3000)
    SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.proceedToCheckoutAgain).click()
  }

  When("""^the website asks me to register in order to finalize checkout$"""){ () =>
    SeleniumWorld.myUser = Some(User(s"selenium.test.${new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date())}@test.com", "MatteoBullo123!"))
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.emailCreate).sendKeys(SeleniumWorld.myUser.get.email)
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Registration.submitCreate).click()
  }

  When("""^I select the shipping address$"""){ () =>
    SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.proceedToCheckoutAgain).click()
    SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.processAddress).click()
  }

  When("""^I select a delivery method$"""){ () =>
    SeleniumWorld.driver.get.findElementById(ObjectRepository.Checkout.agreeServiceTerms).click()
    SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.processCarrier).click()
  }

  When("""^I select a payment method$"""){ () =>
    SeleniumWorld.driver.get.findElementByClassName(ObjectRepository.Checkout.payByCheck).click()
  }

  When("""^I confirm I want to place the order$"""){ () =>
    SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.confirmOrder).click()
  }

  Then("""^the order is successfully finalized$"""){ () =>
    assert(SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.orderConfirmed).isDisplayed ,
      "Something gone wrong! You were not redirected to the account creation page!")

    assert(SeleniumWorld.driver.get.findElementByXPath(ObjectRepository.Checkout.orderIsCompleteBox).isDisplayed ,
      "Something gone wrong! You were not redirected to the account creation page!")
    println("Everything worked fine, the order is successfully finalized!")
  }
}
