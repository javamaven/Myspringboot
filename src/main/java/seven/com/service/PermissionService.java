package seven.com.service;

import org.springframework.cache.annotation.Caching;
import seven.com.domain.SysPermission;

import java.security.Permission;

/**
 * Created by chenhaijun on 2017/1/15.
 */
public interface PermissionService {

    @Caching()
    public SysPermission findByUrl(String url);

}
