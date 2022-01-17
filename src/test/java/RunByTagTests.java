import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports",
                "json:target/cucumber.json",
                "at.utils.allure.AllureCucumberJvmOverride",
                "rerun:target/rerun.txt"},
        features = "src/test/java",
        glue = "at.steps",
//        tags = "@Integration and @OldCash and @Ready and @Final and @ScoringMock"
//        tags = "@LegendOfTomorrow"
        tags = "@Ready"
//        tags = "@Ksenia"
)

public class RunByTagTests
{
}
