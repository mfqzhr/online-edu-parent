import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/4 13:37
 * @description：启动类
 * @modified By：
 * @version: v1$
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.mfq")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
