package seven.com.MyEnvironmentAware;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by chenhaijun on 2017/1/6.
 */
@ConfigurationProperties(prefix = "seven",locations = "classpath:my.properties")
public class Myseven {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
