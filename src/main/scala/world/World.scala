package world

import entities.User

object World {

  var myUser : Option[User] = None

  var myRegistrationResponseCode : Option[Int] = None

  var myLoginResponseCode : Option[Int] = None
}
