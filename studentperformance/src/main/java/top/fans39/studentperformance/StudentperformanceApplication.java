package top.fans39.studentperformance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.fans39.studentperformance.dao")
public class StudentperformanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentperformanceApplication.class, args);
	}

}
