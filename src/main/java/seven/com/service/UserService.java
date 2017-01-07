package seven.com.service;

/**
 * Created by chenhaijun on 2017/1/5.
 */

import com.google.common.collect.Lists;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import seven.com.dao.UserJdbcDao;
import seven.com.dao.UserDao;
import seven.com.domain.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserDao userdao;

    @Resource
    UserJdbcDao userJdbcDao;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Cacheable(value = "mykey4",keyGenerator = "keyGenerator")
    public String test(){

        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("mykey4", "random1="+Math.random());
        System.out.println(valueOperations.get("mykey4"));
        return "ok";
    }

    @CachePut(value={"user,users"})
    public User save(User user){
        return userdao.save(user);
    }

    @Cacheable(value = "user",keyGenerator = "keyGenerator")
    public User loadUser(Long id){
        System.out.println("-----------测试数据--"+id+"UserService-----loadUser");
       return  userJdbcDao.loadUser(id);
    }

    @CacheEvict(value={"user,users"})
    public void deleteUser(Long id){
        userdao.delete(id);
    }
    @Cacheable(value="users",keyGenerator = "keyGenerator")
    public List<User> listUser(){
        System.out.println("-----------打印测试数据--userlist()----UserService-----listUser");
       return Lists.newArrayList(userdao.findAll());

    }
}
