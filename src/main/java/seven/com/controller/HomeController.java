package seven.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/1/11.
 */
@Controller
public class HomeController {

    @RequestMapping(value="/login")
    public String login(){
       return "login";
    }

    @RequestMapping(value="/index")
    public String index(){
        return "index";
    }

}
