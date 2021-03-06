package api.manager

import com.google.gson.Gson
import constants.MyConstants
import scalaj.http.{Http, HttpResponse}
import api.world.World

object HTTPManager {
  def register(): HttpResponse[String] = {

    val body = new Gson
    val bodyJSON = body.toJson(World.myUser.get)

    Http(s"${MyConstants.apiBaseURL}users")
      .timeout(10000, 10000)
      .method("POST")
      .postData(bodyJSON)
      .asString
  }

  def login(): HttpResponse[String] = {

    val body = new Gson
    val bodyJSON = body.toJson(World.myUser.get)

    Http(s"${MyConstants.apiBaseURL}login")
      .timeout(10000, 10000)
      .header("Content-Type", "application/json")
      .method("POST")
      .postData(bodyJSON)
      .asString
  }

  def loginWithMalformedBody(): HttpResponse[String] = {

    val malformedBody : String = s"""{"email":"${World.myUser.get.email}"}"""

    Http(s"${MyConstants.apiBaseURL}login")
      .timeout(10000, 10000)
      .header("Content-Type", "application/json")
      .method("POST")
      .postData(malformedBody)
      .asString
  }
}
