package seven.com.service;

import seven.com.domain.SysUser;

import java.util.List;

/**
 * Created by chenhaijun on 2017/1/12.
 */
public interface UserService {

    SysUser loadUserByUsername(String username);
    SysUser loadUser(Long id);
    boolean deleteUser(Long id);
    List<SysUser> listUser();


}
