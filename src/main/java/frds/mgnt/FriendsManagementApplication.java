package frds.mgnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class})
public class FriendsManagementApplication {

	  @Bean
	    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
	        return builder -> {
	            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
	        };
	    }

	public static void main(String[] args) {
		SpringApplication.run(FriendsManagementApplication.class, args);
		System.out.println("hello"); 
		
	}

}

