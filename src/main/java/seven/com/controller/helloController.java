package seven.com.controller;

import org.apache.coyote.http2.ByteUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import seven.com.MyEnvironmentAware.TestMyproperites;

import javax.annotation.Resource;

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


        String passwordmd5 = Md5Hash.toString("12345".getBytes());

        System.out.println("--------------------passwordmd5="+passwordmd5+","+"当前类=helloController.testpassword()");

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
