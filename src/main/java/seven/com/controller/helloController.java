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
    public String hello(){

        return "jsp/index";

    }

}
