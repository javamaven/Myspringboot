package seven.com.controller;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import seven.com.MyEnvironmentAware.Myseven;
import seven.com.MyEnvironmentAware.TestMyproperites;
import seven.com.domain.User;
import seven.com.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping(value="/users")     // 通过这里配置使下面的映射都在/users下，可去除
@ConditionalOnClass(TestMyproperites.class)
@EnableConfigurationProperties(TestMyproperites.class)
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @Resource
    UserService userService;



    @Autowired
    private Myseven myseven;

    @RequestMapping(value="index")
    public ModelAndView index(HttpSession httpSession){
        httpSession.setAttribute("mylove","seven");
        ModelAndView view = new ModelAndView("page/user/index");
        return view;
    }

    @RequestMapping(value="login")
    public ModelAndView login(HttpSession httpSession){
        httpSession.setAttribute("mylove","seven");
        ModelAndView view = new ModelAndView("page/user/login");
        return view;
    }



    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public ModelAndView getUserList() {
        List<User> r = new ArrayList<User>();
        ModelAndView view = new ModelAndView("page/user/list");
        view.addObject("msg","hahah");

        System.out.println("-----------打印测试数据--myseven"+myseven.getName()+"UserController-----getUserList");
/*
        User user = new User();
        user.setName("ddd");
        user.setPassword("fdgdfg");
        userService.save(user);
        user = userService.loadUser(1L);
        view.addObject("user",user);
        System.out.println("fdgdfgdfgdfgdfgdfg");
        System.out.println("--------------------tets="+testMyproperites.getName()+","+"当前类=helloController.hello()");

        System.out.println("--------------------myseven="+myseven.getName()+","+"当前类=UserController.getUserList()");
*/

        view.addObject("users",userService.listUser());

        return view;
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ModelAndView getUser(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("page/user/detail");
        view.addObject("user", userService.loadUser(id));
        return view;
    }

    @RequestMapping(value="create/{id}", method=RequestMethod.GET)
    public ModelAndView postUser(@PathVariable Long id){
        ModelAndView view = new ModelAndView("page/user/edit");
        User user = new User();
        if(id!=null) {
            user = userService.loadUser(id);

        }
        view.addObject("msg","");
        view.addObject("user",user);
        return  view;

    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        User user = userService.loadUser(id);

        if(user != null){
            if(userService.deleteUser(id)){
             return "success";
            }
        }
        return "erro";

    }


    @RequestMapping("/getSession")
    public String session(HttpSession session) {

        String s = session.getAttribute("mylove").toString();
        System.out.println("-----------打印测试数据--s="+s+"UserController-----session");
        return "getsession";
    }

    @RequestMapping(value="testRedis")
    public void tsetRedis(){

        userService.test();

        System.out.println("-----------打印测试数据--"+userService.listUser().size()+"UserController-----tsetRedis");

    }

    public static Map<Long, User> getUsers() {
        return users;
    }

    public static void setUsers(Map<Long, User> users) {
        UserController.users = users;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}