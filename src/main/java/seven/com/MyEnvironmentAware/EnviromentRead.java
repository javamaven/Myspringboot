package seven.com.MyEnvironmentAware;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by Administrator on 2017/1/6.
 * 注解注入
 *  * 主要是@Configuration，实现接口：EnvironmentAware就能获取到系统环境信息;
 *  void setEnvironment(Environment environment)
 *
 *  还可以通过@ConfigurationProperties 读取application属性配置文件中的属性
 *
 *  http://412887952-qq-com.iteye.com/blog/2292727
 *
 *  需要加入依赖：<!--spring-boot-configuration:spring boot 配置处理器; -->
 <dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-configuration-processor</artifactId>
 <optional>true</optional>
 </dependency>
 *
 *
 * @Configuration
 @ConditionalOnClass(Mongo.class)
 @EnableConfigurationProperties(MongoProperties.class)
 publicclassMongoAutoConfiguration {

 @ConfigurationProperties(prefix = "spring.data.mongodb")
 properities
 */
@Configuration
public class EnviromentRead implements EnvironmentAware{

    //注入application.properties的属性到指定变量中.
    @Value("${spring.datasource.url}")
    private String sqlurl;

    /**
     *注意重写的方法 setEnvironment 是在系统启动的时候被执行。
     */
    @Override
    public void setEnvironment(Environment environment) {

        //打印注入的属性信息.
        System.out.println("myUrl="+sqlurl);

        //通过 environment 获取到系统属性.
        System.out.println(environment.getProperty("JAVA_HOME"));

        //通过 environment 同样能获取到application.properties配置的属性.
        System.out.println(environment.getProperty("spring.datasource.url"));

        //获取到前缀是"spring.datasource." 的属性列表值.
        RelaxedPropertyResolver relaxedPropertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
        System.out.println("spring.datasource.url="+relaxedPropertyResolver.getProperty("url"));
        System.out.println("spring.datasource.driverClassName="+relaxedPropertyResolver.getProperty("driverClassName"));
    }
}
