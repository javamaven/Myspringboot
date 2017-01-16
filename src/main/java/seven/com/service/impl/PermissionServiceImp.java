package seven.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import seven.com.dao.PermissionDao;
import seven.com.domain.SysPermission;
import seven.com.service.PermissionService;

import java.security.Permission;

/**
 * Created by chenhaijun on 2017/1/15.
 */
@Service
public class PermissionServiceImp implements PermissionService{

    @Autowired
    PermissionDao permissionDao;

    @Cacheable(value = "permission",keyGenerator = "wiselyKeyGenerator")
    public SysPermission findByUrl(String url) {
        SysPermission permission = permissionDao.findByUrl(url);

        return permission;
    }


}
