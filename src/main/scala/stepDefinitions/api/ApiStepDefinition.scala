package stepDefinitions.api

import cucumber.api.scala.{EN, ScalaDsl}
import entities.User
import manager.HTTPManager
import world.World

class ApiStepDefinition extends ScalaDsl with EN {

  Given("""^I am a new user$"""){ () =>
    World.myUser = Some(User("Matteo", "Toteda"))
  }

  Given("""^I register on ReqRes website$"""){ () =>
    val response = HTTPManager.register()

    assert(response.code.toString.nonEmpty, "Error during registration!")
    println(s"\nThe response of registration request for user ${World.myUser.get} is : \n${response.body}\n")

    World.myRegistrationResponseCode = Some(response.code)
  }

  When("""^I check if the response code is (.*)$"""){ (code: String) =>
    assert(World.myRegistrationResponseCode.get.toString.equalsIgnoreCase(code), "An Error occured during registration!")
  }

  Given("""^I am a new user with email (.*) and password (.*)$"""){ (email: String, password: String) =>
    World.myUser = Some(User(email, password))
  }

  When("""^I try to login$"""){ () =>
    val response = HTTPManager.login()

    assert(response.code.toString.nonEmpty, "Error during login!")
    println(s"\nThe response of login request for user ${World.myUser.get} is : \n${response.body}\n")

    World.myLoginResponseCode = Some(response.code)
  }

  Then("""^The login is successful$"""){ () =>
    assert(World.myLoginResponseCode.get.toString.equalsIgnoreCase("200"), "An Error occured during login!")
  }

  When("""^I try to login using a wrong body$"""){ () =>
    val response = HTTPManager.loginWithMalformedBody()

    assert(response.code.toString.nonEmpty, "Error during login request!")
    println(s"\nThe response of login request for user ${World.myUser.get} is : \n${response.body}\n")

    World.myLoginResponseCode = Some(response.code)
  }

  Then("""^The login is unsuccessful$"""){ () =>
    assert(World.myLoginResponseCode.get.toString.equalsIgnoreCase("400"), "Login with malformed body was succesful!")
  }
}
