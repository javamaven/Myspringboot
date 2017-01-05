package seven.com.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/1/5.
 */
@Component
public class TestScheduler {

    @Scheduled(cron = "0/20 * * * * ?")
    public void  testprintln(){
        System.out.println("-----------打印测试数据--打打打打打打 TestScheduler-----testprintln");
    }

}
