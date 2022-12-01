package edu.uci.aspectj;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author yuxin huang
 */
@Aspect
@Component
public class ControllerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    private static final String POINT_CUT = "execution(public * edu.uci.controller..*.*(..)) && !execution(public * edu.uci.controller.StaffController.getQrCode(..))";
    private static final String BRACKETS = "()";
    private static final String SPOT = ".";
    private static final String REQUEST_BODY_VALUE = " RequestBody value:";
    private static final String RESPONSE_BODY_VALUE = " ResponseBody value:";

    @Pointcut(POINT_CUT)
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName() + BRACKETS;

        Object[] args = pjp.getArgs();
        Gson gson = new Gson();
        if (args != null) {
            logger.info(className + SPOT + methodName + REQUEST_BODY_VALUE + gson.toJson(args));
        }

        Object obj = pjp.proceed();

        Object logObj = obj;
        if (obj != null) {
            if (obj instanceof List && ((List) obj).size() > 10) {
                logObj = ((List) obj).size();
                logger.info(className + SPOT + methodName + RESPONSE_BODY_VALUE + " list size over 10 only print list size:"+ gson.toJson(logObj));
            }else if(obj instanceof Object){
                logger.info(className + SPOT + methodName + RESPONSE_BODY_VALUE + gson.toJson(logObj));
            }
        }

        return obj;
    }
}
