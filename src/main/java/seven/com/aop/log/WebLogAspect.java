package seven.com.aop.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import seven.com.exception.DefultException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by Administrator on 2017/1/19.
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    /**
     * 定义一个切入点.
     * 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 定义在web包或者子包
     * ~ 第四个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     *  @Pointcut("execution(public * com.kfit.*.web..*.*(..))")
     */
    //下面用到的是织入点语法, 看文档里面有. 就是指定在该方法前执行
    //@Before("execution(public void com.dao.impl.UserDAOImp.save(com.model.User))")
    //记住下面这种通用的, *表示所有
    //@Pointcut("execution(public * com.dao.impl..*.*(..))")
    @Pointcut("execution(public * seven.com.controller..*.*(..))")
    public void webLog(){
        logger.info("-------------------------------------------这是切入点的方法不运行------------------------------------");

    }

    @Before("webLog()")
    public void dobefore(JoinPoint joinPoint) {

        logger.info("-------------------------------------------先运行这里------------------------------------");

        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }



    }

    @AfterReturning("webLog()")
    public void doafter(JoinPoint joinPoint){

        logger.info("-------------------------------------------后运行这里------------------------------------");

        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
        // 处理完请求，返回内容


    }

    @AfterThrowing("webLog()")
    public void dothrow(JoinPoint joinPoint){

        logger.info("-------------------------------------------=异常抛出------------------------------------");

    }


    //环绕, 这个特殊点.
    @Around("webLog()")
    public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable
    {
        //加逻辑的时候, 不要依赖执行的的先后顺序
       logger.info("-------------------------------------------不知道怎么回事------------------------------------");
        System.out.println("执行目标方法之前，模拟开始事务...");
        // 获取目标方法原始的调用参数
        Object[] args = jp.getArgs();
        if(args != null && args.length > 1)
        {
            // 修改目标方法的第一个参数
            args[0] = "【增加的前缀】" + args[0];
        }
        // 以改变后的参数去执行目标方法，并保存目标方法执行后的返回值
        Object rvt = jp.proceed(args);
        System.out.println("执行目标方法之后，模拟结束事务...");
        // 如果rvt的类型是Integer，将rvt改为它的平方
        if(rvt != null && rvt instanceof Integer)
            rvt = (Integer)rvt * (Integer)rvt;
        return rvt;
    }
}
