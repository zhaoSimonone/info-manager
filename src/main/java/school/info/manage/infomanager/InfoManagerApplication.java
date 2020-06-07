package school.info.manage.infomanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//使用mybatis-generator生成的mapper不能自动被spring-boot扫描，需要配置
@SpringBootApplication
@MapperScan("school.info.manage.infomanager.mapper")
public class InfoManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(InfoManagerApplication.class, args);
  }
}
