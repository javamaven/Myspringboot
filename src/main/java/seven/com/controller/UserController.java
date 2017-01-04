package seven.com.controller;

import org.springframework.web.bind.annotation.*;
import seven.com.domain.User;

import java.util.*;

/**
 * Created by chenhaijun on 2017/1/4.
 */
@RestController
@RequestMapping(value="/users")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());


    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<User> listUser(){

        List<User> r = new ArrayList<User>(users.values());
        return r;

    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String saveUser(@ModelAttribute User user){
        users.put(user.getId(),user);
        return "success";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public User loadUser(@PathVariable Long id){
        users.get(id);
        return users.get(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }

}
