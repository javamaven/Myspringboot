package seven.com.service;

/**
 * Created by chenhaijun on 2017/1/5.
 */

import org.springframework.stereotype.Service;
import seven.com.dao.UserDao;
import seven.com.domain.User;

import javax.annotation.Resource;
@Service
public class UserService {
    @Resource
    UserDao userdao;

    public void save(User user){
        userdao.save(user);
    }
}
