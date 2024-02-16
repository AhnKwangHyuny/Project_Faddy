package faddy.backend.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DBAccessTimeTraceAop {
    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void DBAccessMethod() {};

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactionMethod() {};

    @Around("repositoryClassMethods() || transactionalMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            System.out.println("Execution of " + className + "." + methodName + " ended in " + elapsedTime + "ms");
            return result;
        } catch (IllegalArgumentException e) {

            System.out.println("Illegal argument " + joinPoint.getArgs()[0] + " in " + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

}
