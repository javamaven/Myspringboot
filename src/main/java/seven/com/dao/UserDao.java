package seven.com.dao;

import org.springframework.data.repository.CrudRepository;
import seven.com.domain.SysUser;

import java.util.List;

/**
 * Created by chenhaijun on 2017/1/5.
 */

public interface UserDao extends CrudRepository<SysUser,Long> {

    public SysUser findByUsername(String username);


}
