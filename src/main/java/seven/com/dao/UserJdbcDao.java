package seven.com.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import seven.com.domain.SysUser;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/5.
 */
@Repository
public class UserJdbcDao {
    @Resource
    JdbcTemplate jdbcTemplate;

    public SysUser loadUser(Long id){
        String sql = "select * from tb_myuser where id=?";
        RowMapper<SysUser> rowMapper = new BeanPropertyRowMapper<SysUser>(SysUser.class);
        return jdbcTemplate.queryForObject(sql, rowMapper,id);
    }

}
