package seven.com.security;

import javassist.expr.NewArray;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;


/**
 * Shiro 配置
 *
 Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 *
 * @author
 * @version v.0.1
 */
@Configuration
public class ShiroConfiguration {


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");


        //自定义filter
        Map<String,Filter> filters = new LinkedHashMap<String,Filter>();


        LogoutFilter logoutFilter = new LogoutFilter();

        logoutFilter.setRedirectUrl("/login");

        filters.put("logout",logoutFilter);


        SysUserFilter sysUserFilter = new SysUserFilter();

        sysUserFilter.setErrorUrl("unauth");

        filters.put("access",sysUserFilter);


        shiroFilterFactoryBean.setFilters(filters);

        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/users/**", "authc,access");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));

        return hashedCredentialsMatcher;
    }

    /*
    * 	<bean id="userRealm" class="com.seven.chen.ream.UserRealm">
	 	<property name="cachingEnabled" value="true"/>
	 	<property name="authenticationCachingEnabled" value="true"/>
	 	<property name="authenticationCacheName" value="shiro-authenticationCache"/>
	 	<property name="authorizationCachingEnabled" value="true"/>
	 	<property name="authorizationCacheName" value="shiro-authorizationCache"/>
		<property name="credentialsMatcher" ref="hashMatcher"></property>
	</bean>
    *
    * */


    @Bean
    public  UserRealm userRealm(){

        UserRealm userRealm = new UserRealm();

        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        userRealm.setCachingEnabled(true);

        userRealm.setAuthenticationCachingEnabled(true);
        userRealm.setAuthenticationCacheName("shiro-authenticationCache");

        userRealm.setAuthorizationCachingEnabled(true);
        userRealm.setAuthorizationCacheName("shiro-authorizationCache");

        return userRealm;

    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    /*启用注解
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();

        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);

        return  authorizationAttributeSourceAdvisor;

    }
    */

    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehCacheManager;
    }

}

