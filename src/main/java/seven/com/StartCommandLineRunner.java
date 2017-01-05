package seven.com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by chenhaijun on 2017/1/6.
 * order 执行顺序
 */
@Component
@Order(value=1)
public class StartCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        String name = "服务器启动服务 可做初始化操作";
        System.out.println("--------------------启动了="+name+","+"当前类=StartCommandLineRunner.run()");
    }
}
