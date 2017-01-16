package seven.com.security;

import com.mysql.jdbc.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import seven.com.domain.SysPermission;
import seven.com.domain.SysRole;
import seven.com.domain.SysUser;
import seven.com.service.PermissionService;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Permission;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenhaijun on 2017/1/15.
 */
public class SysUserFilter extends AccessControlFilter {

    private String errorUrl;

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {


        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/"+errorUrl);

        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        Subject subject = getSubject(request,response);

        SysUser user = (SysUser) subject.getPrincipal();

        //获取URL 通过url 查询 permission;

        String url = getPathWithinApplication(request);

        System.out.println("--------------------url="+url+","+"当前类=SysUserFilter.isAccessAllowed()");

        Map<String,String > map = new HashMap<String,String >();

        for(SysRole role :user.getSysRoles()){
            for(SysPermission permission : role.getSysPermissions()){
                map.put(permission.getUrl(),permission.getPermission());
            }
        }

        String permission = map.get(url);

        if(StringUtils.isNullOrEmpty(permission)){
            return false;
        }else{

            return(subject.isPermitted(permission));

        }

    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }
}
