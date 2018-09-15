package selenium.objectDescriptors

object ObjectRepository {

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

  object Checkout{
    val tshirtsCategory = "/html/body[@id='index']/div[@id='page']/div[@class='header-container']/header[@id='header']/div[3]/div[@class='container']/div[@class='row']/div[@id='block_top_menu']/ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[3]/a"//"//a[contains(@title, 'T-shirts')]"
    val firstProductContainer = "//img[contains(@class, 'replace-2x img-responsive') and contains(@title, 'Faded Short Sleeve T-shirts')]"
    val viewCart = "//a[contains(@title, 'View my shopping cart')]"
    val addToCart = "//a[contains(@class, 'button ajax_add_to_cart_button btn btn-default') and contains(@data-id-product, '1')]"
    val proceedToCheckout = "//a[contains(@title, 'Proceed to checkout')]"
    val proceedToCheckoutAgain = "//p//a[contains(@title, 'Proceed to checkout')]"
    val processAddress= "//p//button[contains(@name, 'processAddress')]"
    val agreeServiceTerms = "cgv"
    val processCarrier= "//p//button[contains(@name, 'processCarrier')]"
    val payByCheck = "cheque"
    val confirmOrder = "//button[contains(@type, 'submit') and contains(@class, 'button btn btn-default button-medium')]"
    val orderConfirmed = "//p[contains(@class, 'alert alert-success')]"
    val orderIsCompleteBox = "//div[contains(@class, 'box order-confirmation')]"
  }
}
