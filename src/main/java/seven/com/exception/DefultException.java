package seven.com.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenhaijun on 2017/1/5.
 */
@ControllerAdvice
public class DefultException {

    @ExceptionHandler(value=Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e)  {

        ModelAndView view = new ModelAndView("erro/erro");
        System.out.println("GlobalDefaultExceptionHandler.defaultErrorHandler()");
        System.out.println("--------------------");
        e.printStackTrace();
        String msg = "系统出错了，请重试！";
        view.addObject("msg",msg);
        return view;
       /*
        * 返回json数据或者String数据：
        * 那么需要在方法上加上注解：@ResponseBody
        * 添加return即可。
        */

       /*
        * 返回视图：
        * 定义一个ModelAndView即可，
        * 然后return;
        * 定义视图文件(比如：error.html,error.ftl,error.jsp);
        *
        */
        }

}
