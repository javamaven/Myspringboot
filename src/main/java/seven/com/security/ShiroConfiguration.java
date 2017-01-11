package seven.com.security;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
/**
 * Created by Administrator on 2017/1/10.
 * /**
 * Shiro 配置
 *
 Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 *
 * @author Angel(QQ:412887952)
 * @version v.0.1
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
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        System.out.println("-----------打印测试数据--初始化权限管理器-ShiroConfiguration-----shiroFilter");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);




        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        return securityManager;
    }



}
