package seven.com.controller;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import seven.com.domain.User;
import seven.com.service.UserService;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.*;

@RestController
@RequestMapping(value="/users")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @Resource
    UserService userService;

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public ModelAndView getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        ModelAndView view = new ModelAndView("jsp/user/detail");
        view.addObject("msg","hahah");
        User user = new User();
        user.setName("ddd");
        user.setPassword("fdgdfg");
        userService.save(user);
        user = userService.loadUser(1L);
        view.addObject("user",user);
        System.out.println("fdgdfgdfgdfgdfgdfg");
        return view;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
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
        System.out.println("--------------------id="+id+","+"当前类=UserController.getUser()");
        User user = new User();
        user.setName("dfgdfg");
        user.setPassword("dfgdfg");
        user.setUdateTime(new Date());
        userService.save(user);
        return users.get(id);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
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