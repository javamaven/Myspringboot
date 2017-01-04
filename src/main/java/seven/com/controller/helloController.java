package seven.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import seven.com.domain.User;

import java.util.Map;

/**
 * Created by chenhaijun on 2017/1/3.
 */
@Controller
public class helloController {

    private String msg = "123456";

    @RequestMapping(value="hello")
    public String hello(Map<String,Object> model){
        User user = new User();
        user.setPassword("2334");
        user.setName("chenhaij");

        model.put("user",user);
        model.put("msg",msg);

        return "jsp/index.ftl";

    }

}
