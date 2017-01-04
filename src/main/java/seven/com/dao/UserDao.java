package seven.com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import seven.com.domain.User;

import javax.annotation.Resource;

/**
 * Created by chenhaijun on 2017/1/5.
 */

public interface UserDao extends CrudRepository<User,Long> {

}
