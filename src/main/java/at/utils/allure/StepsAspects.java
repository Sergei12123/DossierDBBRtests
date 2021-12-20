package at.utils.allure;

import at.helpers.HookHelper;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.util.AspectUtils;
import io.qameta.allure.util.ResultsUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Aspect
@Log4j2
public class StepsAspects {
    private static InheritableThreadLocal<AllureLifecycle> lifecycle = new InheritableThreadLocal<AllureLifecycle>() {
        @Override
        protected AllureLifecycle initialValue() {
            return Allure.getLifecycle();
        }
    };
    private static InheritableThreadLocal<Scenario> scenario = new InheritableThreadLocal<>();


    public StepsAspects() {
        //Sonar, go away!
    }

    public static void setScenario(Scenario sc){
        scenario.set(sc);
    }

    public static Scenario getScenario(){
        return scenario.get();
    }

    public static AllureLifecycle getLifecycle() {
        return lifecycle.get();
    }

    public static void setLifecycle(AllureLifecycle allure) {
        lifecycle.set(allure);
    }

    @Pointcut("@annotation(io.qameta.allure.Step)")
    public void withStepAnnotation() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.А)")
    public void withCucumberStepAnnotation1() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Дано)")
    public void withCucumberStepAnnotation2() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Допустим)")
    public void withCucumberStepAnnotation3() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Если)")
    public void withCucumberStepAnnotation4() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Затем)")
    public void withCucumberStepAnnotation5() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.И)")
    public void withCucumberStepAnnotation6() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Иначе)")
    public void withCucumberStepAnnotation7() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Когда)")
    public void withCucumberStepAnnotation8() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Ктомуже)")
    public void withCucumberStepAnnotation9() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Но)")
    public void withCucumberStepAnnotation10() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Пусть)")
    public void withCucumberStepAnnotation11() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Также)")
    public void withCucumberStepAnnotation12() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.То)")
    public void withCucumberStepAnnotation13() {
        //Sonar, go away!
    }

    @Pointcut("@annotation(io.cucumber.java.ru.Тогда)")
    public void withCucumberStepAnnotation14() {
        //Sonar, go away!
    }

    @Pointcut("execution(* *(..))")
    public void anyMethod() {
        //Sonar, go away!
    }

    @Before("anyMethod() && withStepAnnotation()")
    public void stepStart(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Step step = methodSignature.getMethod().getAnnotation(Step.class);
        String name = AspectUtils.getName(step.value(), joinPoint);
        log.info("Начало шага ".concat(name));
    }


    @AfterReturning(
            pointcut = "anyMethod() && withStepAnnotation()"
    )
    public void stepStop(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Step step = methodSignature.getMethod().getAnnotation(Step.class);
        String name = AspectUtils.getName(step.value(), joinPoint);
        log.debug("Шаг ".concat(name).concat(" выполнился"));
    }
}
