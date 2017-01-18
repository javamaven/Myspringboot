package seven.com.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import seven.com.domain.SysPermission;
import seven.com.domain.SysRole;
import seven.com.domain.SysUser;
import seven.com.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenhaijun on 2017/1/15.
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    //登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        System.out.println("--------------------password="+password+","+"当前类=UserRealm.doGetAuthenticationInfo()");
        SysUser user = userService.loadUserByUsername(username);
        if(user == null){
            throw  new UnknownAccountException("---用户不存在----");
        }
        SimpleAuthenticationInfo Info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        Info.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
        return Info;

    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        SysUser user = (SysUser) principal.getPrimaryPrincipal();

        List<SysRole> roles = user.getSysRoles();

        if(roles != null && roles.size()>0 ){

            for(SysRole role : roles){
                info.addRole(role.getRole());

                for(SysPermission permission : role.getSysPermissions()){
                    info.addStringPermission(permission.getPermission());
                }
            }

        }
        return info;
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        //授权存的key是user对象  principals就是一个user对象
        System.out.println("-------------clear Authorization-----------------");


        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        //验证存的key是张三
        System.out.println("-----------------clear Authentication---------------");
        SysUser user = (SysUser)principals.getPrimaryPrincipal();
        SimplePrincipalCollection spc = new SimplePrincipalCollection(user.getUsername(),getName());

        super.clearCachedAuthenticationInfo(spc);
    }

}
