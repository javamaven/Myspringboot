package seven.com.dao;

import org.springframework.data.repository.CrudRepository;
import seven.com.domain.SysPermission;

/**
 * Created by chenhaijun on 2017/1/15.
 */
public interface PermissionDao extends CrudRepository<SysPermission,Long>{

    public SysPermission findByUrl(String url);

}
