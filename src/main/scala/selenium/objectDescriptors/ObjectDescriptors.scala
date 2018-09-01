package selenium.objectDescriptors

object ObjectDescriptors {

  object BattleNetRegistrationDescriptors {
    val firstname = "firstName"
    val lastName = "lastName"
    val dayOfBirth = "dobDay"
    val monthOfBirth = "select-box-dobMonth"
    val monthSelection = "//div[contains(@data-value, '2')]"
    val yearOfBirth = "dobYear"
    val email = "emailAddress"
    val password = "password"
    val selectSecretQuestion = "select-box-question1"
    val secretQuestionValue = "//div[contains(@data-value, '20')]"
    val secretAnswer = "answer1"
    val agreePrivacy = "agreedToPrivacyPolicy"
    val createButton = "creation-submit-button"
  }

  object Registration{
    val emailCreate = "email_create"
    val submitCreate = "SubmitCreate"
    val gender = "id_gender1"
    val firstName  = "customer_firstname"
    val lastName = "customer_lastname"
    val email = "email"
    val password = "passwd"
    val day = "days"
    val month = "months"
    val year = "years"
    val addressFirstName = "firstname"
    val addressLastName = "lastname"
    val address = "address1"
    val city = "city"
    val state = "id_state"
    val CAP = "postcode"
    val Country = "id_country"
    val mobile = "phone_mobile"
    val addressAlias = "alias"
    val submitAccountButton = "submitAccount"
    val accountArea = "//h1[contains(@class, 'page-heading') and .//text()='My account']"
  }

  object LoginAndLogout{
    val loginButton = "login"
    val logoutButton = "logout"
  }

}
