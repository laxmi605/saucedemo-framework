package testSuite;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = {"src/test/java/testSuite/SauceDemo.feature"},
        glue= {"testSuite"},
        tags= "@SauceDemo"
        
)
@RunWith(Cucumber.class)
public class SauceDemo_TestRunner 
{

}
