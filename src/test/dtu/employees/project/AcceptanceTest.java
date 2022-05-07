package dtu.employees.project;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
		plugin = { "summary", "html:target/cucumber/wikipedia.html"},
		monochrome=true,
		snippets = SnippetType.CAMELCASE,
		glue = {"dtu.employees.project"})
public class AcceptanceTest {

}
