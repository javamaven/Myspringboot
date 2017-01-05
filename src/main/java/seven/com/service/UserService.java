package seven.com.service;

/**
 * Created by chenhaijun on 2017/1/5.
 */

import org.springframework.stereotype.Service;
import seven.com.dao.UserJdbcDao;
import seven.com.dao.UserDao;
import seven.com.domain.User;

import javax.annotation.Resource;
@Service
public class UserService {
    @Resource
    UserDao userdao;

    @Resource
    UserJdbcDao userJdbcDao;

    public void save(User user){
        userdao.save(user);
    }

    public User loadUser(Long id){
       return  userJdbcDao.loadUser(id);
    }
}
