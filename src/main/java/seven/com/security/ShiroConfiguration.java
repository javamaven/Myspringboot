package seven.com.security;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/1/10.
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

        System.out.println("-----------打印测试数据--"++"ShiroConfiguration-----shiroFilter");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);



    }

}
