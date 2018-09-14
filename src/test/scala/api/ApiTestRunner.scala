package api

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:api"),
  glue = Array("classpath:api.stepDefinitions"),
  monochrome = true,
  plugin = Array("pretty",
    "html:target/cucumber/test-report.html",
    "json:target/cucumber/test-report.json",
    "junit:target/cucumber/test-report.xml")
)
class ApiTestRunner {
}
