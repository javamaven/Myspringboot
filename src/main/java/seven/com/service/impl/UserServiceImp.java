package seven.com.service.impl;

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
import seven.com.domain.SysUser;
import seven.com.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Resource
    UserDao userdao;

    @Resource
    UserJdbcDao userJdbcDao;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Cacheable(value = "mykey4")
    public String test(){

        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("mykey4", "random1="+Math.random());
        System.out.println(valueOperations.get("mykey4"));
        return "ok";
    }

    @CachePut(value={"sysUser,users"})
    public SysUser save(SysUser sysUser){
        return userdao.save(sysUser);
    }

    @Override
    public SysUser loadUserByUsername(String username) {
        return userdao.findByUsername(username);
    }

    public SysUser loadUser(Long id){
        System.out.println("-----------测试数据--"+id+"UserService-----loadUser");
        SysUser sysUser = new SysUser();
        try {
            sysUser = userdao.findOne(id);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return sysUser;
        }

    }

    @CacheEvict(value={"user,users"})
    public boolean deleteUser(Long id){
        boolean flag = false;
        try{
            userdao.delete(id);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return flag;
        }

    }


    @Cacheable(value="users",keyGenerator = "wiselyKeyGenerator")
    public List<SysUser> listUser(){
        System.out.println("-----------打印测试数据--userlist()----UserService-----listUser");

       return Lists.newArrayList(userdao.findAll());

    }
}
