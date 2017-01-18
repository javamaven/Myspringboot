package seven.com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import seven.com.domain.SysUser;

/**
 * Created by Administrator on 2017/1/11.
 */
@Controller
public class HomeController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public ModelAndView  login(){

        logger.info("-----------------------------------------测试日志记录------------------------------");
        return new ModelAndView("login");
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute SysUser sysUser){
        ModelAndView view = null;

        Subject subject = SecurityUtils.getSubject();

        System.out.println("--------------------sysUser="+sysUser.getUsername()+","+"当前类=HomeController.login()");

        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());

        String securitypassword = new Md5Hash("12345", "admin").toHex();

        logger.info("-----------------------------------------测试日志记录------------------------------");

        System.out.println("--------------------securitypassword="+securitypassword+","+"当前类=HomeController.login()");
        
        try{

            subject.login(token);

            view = new ModelAndView("page/user/index");

        }catch (UnknownAccountException e){

            System.out.println("--------------------="+"用户不存在"+","+"当前类=HomeController.login()");
            e.printStackTrace();

            view = new ModelAndView("login");

            view.addObject("msg","用户名或者密码错误");

        }catch (IncorrectCredentialsException ee) {

            System.out.println("--------------------=" + "密码不正确" + "," + "当前类=HomeController.login()");
            ee.printStackTrace();

            view = new ModelAndView("login");

            view.addObject("msg", "用户名或者密码错误");
        }finally {
            return view;
        }


    }

    @RequestMapping(value="/index")
    public String index(){


        return "index";


    }

    @RequestMapping(value="unauth")
    public ModelAndView unauth(){
        return new ModelAndView("unauth");
    }



}
