import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java",
        glue = "at.steps",
//        tags = "@Integration and @OldCash and @Ready and @Final and @ScoringMock"
        tags = "@Ready"
)
public class RunByTagTests {
}