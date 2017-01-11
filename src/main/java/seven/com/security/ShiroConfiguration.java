package seven.com.security;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/10.
 ** Shiro 配置
 *
 Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 */

@Configuration
public class ShiroConfiguration {

    /**
     * ShiroFilterFactoryBaen 处理拦截资源文件问题
     * 注意 单独一个shiroFilterFactory 配置是会报错 因为初始化的时候需要注入manager
     * filter chain 定义说明
     * 1.一个url可以配置多个filter
     * 2.当设置多个过滤器时，全部验证通过，才视为通过
     * 3.部分过滤器可指定参数 如perm roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        System.out.println("-----------打印测试数据--shirofilter初始化---------ShiroConfiguration-----shiroFilter");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置权限管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置登录 首页 错误页面 无权限页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorise");
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //拦截器设置
        Map<String,String> filterChainDefinitionMap  = new LinkedHashMap<String,String>();

        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout","logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return  shiroFilterFactoryBean;


    }

    @Bean
    public SecurityManager securityManager(){
        //返回默认的web权限管理器
        return new DefaultWebSecurityManager();
    }

}
