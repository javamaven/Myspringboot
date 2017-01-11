package seven.com;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import seven.com.MyEnvironmentAware.Myseven;
import seven.com.servletOrfilter.MyServlet;
import seven.com.util.Springutil;

import javax.servlet.MultipartConfigElement;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenhaijun on 2017/1/3.
 */
@SpringBootApplication
@EnableScheduling
@ServletComponentScan
@EnableConfigurationProperties({Myseven.class})
public class DemoApplication {

    /**
     * * fastJson 除了添加依赖之外还可以有2种方法
    * 1（1）启动类继承extends WebMvcConfigurerAdapter
     * （2）覆盖方法configureMessageConverters
    * */

    /**
     *方法2
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig;
        fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

    /**
     * 代码注册通过ServletRegistrationBean、 FilterRegistrationBean
     *  和 ServletListenerRegistrationBean 获得控制。
     *也可以通过实现 ServletContextInitializer 接口直接注册。
     * @param
     */
    @Bean
    public ServletRegistrationBean MyServlet(){

        return new ServletRegistrationBean(new MyServlet(),"/myServlet/*");

    }

    /**
     * 文件大小限制
     */

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
        factory.setMaxFileSize("5MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("10MB");
        //Sets the directory location where files will be stored.
        //factory.setLocation("路径地址");
        return factory.createMultipartConfig();
    }

    public static void main(String args[]){



        ApplicationContext ctx = SpringApplication.run(DemoApplication.class,args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for(String beanName : beanNames) {
            System.out.println(beanName);
        }

    }





    /** 方法1
     @Override
     @SpringBootApplication
     public class ApiCoreApp  extends WebMvcConfigurerAdapter {

     @Override
     public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
     super.configureMessageConverters(converters);

     FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

     FastJsonConfig fastJsonConfig = new FastJsonConfig();
     fastJsonConfig.setSerializerFeatures(
     SerializerFeature.PrettyFormat
     );
     fastConverter.setFastJsonConfig(fastJsonConfig);

     converters.add(fastConverter);
     }
     }
     **/
}
