package dtu.project;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "use_cases", 
	plugin = { "summary", "html:target/cucumber/wikipedia.html"}, 
	monochrome=true, 
	snippets = SnippetType.CAMELCASE, 
	glue = { "dtu.library.project", "dtu.library.student_tests"})
public class AcceptanceTest {

}
