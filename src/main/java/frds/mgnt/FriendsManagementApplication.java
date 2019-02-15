package frds.mgnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@SpringBootApplication
public class FriendsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendsManagementApplication.class, args);
		System.out.println("hello"); 
		
	}

}