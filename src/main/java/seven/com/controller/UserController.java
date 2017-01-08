package seven.com.controller;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import seven.com.MyEnvironmentAware.Myseven;
import seven.com.MyEnvironmentAware.TestMyproperites;
import seven.com.domain.User;
import seven.com.service.UserService;
import javax.annotation.Resource;
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

    @RequestMapping(value="testRedis")
    public void tsetRedis(){

        userService.test();

        System.out.println("-----------打印测试数据--"+userService.listUser().size()+"UserController-----tsetRedis");

    }

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public ModelAndView getUserList() {
        List<User> r = new ArrayList<User>();
        ModelAndView view = new ModelAndView("jsp/user/detail");
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

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="user", method=RequestMethod.GET)
    public String postUser(@RequestBody User user) {

        user = new User();

        user.setName("wozaid");
        user.setPassword("nihaodf");
        user.setUdateTime(new Date());

        userService.save(user);

        System.out.println("--------------------user.getId()="+user.getId()+","+"当前类=UserController.postUser()");
        System.out.println("--------------------user.getId()="+user.getId()+","+"当前类=UserController.postUser()");
        System.out.println("--------------------user.getId()="+user.getId()+","+"当前类=UserController.postUser()");
        System.out.println("--------------------user.getId()="+user.getId()+","+"当前类=UserController.postUser()");
        return "success";
    }


    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.loadUser(id);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/updateuser", method=RequestMethod.PUT)
    public String putUser() {

        User user = new User();
        user.setId(1L);
        user.setPassword("789");

        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="delete/{id}", method=RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "success";
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