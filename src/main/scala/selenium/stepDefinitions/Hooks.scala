package selenium.stepDefinitions

import cucumber.api.java.After
import selenium.world.SeleniumWorld

class Hooks {

  @After
  def tearDown(): Unit = {
    SeleniumWorld.driver.get.quit()
  }
}
