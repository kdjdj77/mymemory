package io.cloudtype.mymemory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MymemoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MymemoryApplication.class, args);
	}

}
