package seven.com.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by chenhaijun on 2017/1/5.
 *  启动程序的时候将ApplicationContext 注入到此工具类中作为一个静态变量 可在其他任意地方使用获取bean
 * 工具类获取 WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
 * 继承自抽象类ApplicationObjectSupport
 * 继承自抽象类WebApplicationObjectSupport
 * 实现接口ApplicationContextAware
 * --------------------------------------
 * 不用包扫描方式  通过在启动程序中bean注入util
 *@Bean
 *public SpringUtil springUtil2(){return new SpringUtil();}
 *
 *在启动程序中 @Import(value={SpringUtil.class}) 导入
 */
//注解包扫描方式交给spring管理 启动的时候同步注入applicationContext
@Component
public class Springutil implements ApplicationContextAware{

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(applicationContext == null){
            System.out.println("--------------注入失败 不能生效------applicationContext=当前类=Springutil.setApplicationContext()");
        }else{

            if(Springutil.applicationContext == null){
                Springutil.applicationContext = applicationContext;
                if(Springutil.applicationContext != null) {
                    System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象" + applicationContext + ",applicationContext=" + Springutil.applicationContext + "========");
                    System.out.println("---------------------------------------------------------------------");
                }
                }

        }

    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
