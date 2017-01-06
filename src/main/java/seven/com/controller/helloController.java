package seven.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenhaijun on 2017/1/3.
 */
@Controller
@Configuration
public class helloController implements EnvironmentAware{

    private String msg = "123456";

    private String url ;

    @RequestMapping(value="hello")
    public String hello(){
        return "jsp/index";

    }

    @RequestMapping(value="mytest")
    public void test(){
        System.out.println("--------------------=当前类=helloController.test()");
        throw new RuntimeException();
    }

    @RequestMapping(value="enviroment")
    public String testEnviroment(){

        System.out.println("-----------打印测试数据--url"+url+"helloController-----testEnviroment");
        return url;

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("---------------------------------------------------------------------");
        url = environment.getProperty("spring.datasource.url");
    }
}
