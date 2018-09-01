package selenium.world

import entities.User
import org.openqa.selenium.chrome.ChromeDriver

object SeleniumWorld {

  var myUser : Option[User] = None

  var driver : Option[ChromeDriver] = None
}
