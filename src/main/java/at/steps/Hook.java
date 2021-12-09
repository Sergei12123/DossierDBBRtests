package at.steps;

import at.helpers.HookHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hook {
//    @BeforeEach
//    @Order(1)
//    public void initializeDB(){
//
//    }
    @Before(order=1)
    public void initializeBrowser(){
        HookHelper.initWebDriver();
    }

    @After(order=1)
    public void closeBrowser(){
        HookHelper.clearWebDriver();
    }
}
