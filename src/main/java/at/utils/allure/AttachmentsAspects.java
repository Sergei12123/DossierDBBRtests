package at.utils.allure;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import io.qameta.allure.util.AspectUtils;
import io.qameta.allure.util.NamingUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Работа с allure
 */
@Aspect
public class AttachmentsAspects {

    private static InheritableThreadLocal<AllureLifecycle> lifecycle = new InheritableThreadLocal<AllureLifecycle>()
    {
        @Override
        protected AllureLifecycle initialValue()
        {
            return Allure.getLifecycle();
        }
    };

    public AttachmentsAspects() {
    }

    public static AllureLifecycle getLifecycle() {
        return lifecycle.get();
    }

    public static void setLifecycle(AllureLifecycle allure) {
        lifecycle.set(allure);
    }

    @Pointcut("@annotation(io.qameta.allure.Attachment)")
    public void withAttachmentAnnotation() {
    }

    @Pointcut("execution(* *(..))")
    public void anyMethod() {
    }

    @AfterReturning(
            pointcut = "anyMethod() && withAttachmentAnnotation()",
            returning = "result"
    )
    public void attachment(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Attachment attachment = methodSignature.getMethod().getAnnotation(Attachment.class);
        byte[] bytes = result instanceof byte[] ? (byte[]) result : Objects.toString(result).getBytes(StandardCharsets.UTF_8);
        String name = attachment.value().isEmpty() ? methodSignature.getName() : NamingUtils.processNameTemplate(attachment.value(), AspectUtils.getParametersMap(joinPoint));
    }

}
