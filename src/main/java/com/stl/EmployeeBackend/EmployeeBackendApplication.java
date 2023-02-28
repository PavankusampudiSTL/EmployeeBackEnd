package com.stl.EmployeeBackend;

//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.stl.EmployeeBackend.model.Employee;
//import com.stl.EmployeeBackend.repository.EmployeeRepository;

@SpringBootApplication
public class EmployeeBackendApplication {
	
//	@Autowired
//	private EmployeeRepository repository;
//	
//	public void Ussers() {
//		List<Employee> users = Stream.of(new Employee(9,"pavan02","kusampudi pavan","pavankusampudi@gmail.com","9959377872","12345678")
//				).collect(Collectors.toList());
//		repository.saveAll(users);
//	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeBackendApplication.class, args);
		
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // TODO Auto-generated method stub
                WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("*").maxAge(3600L)
                .allowedHeaders("*").exposedHeaders("Authorization").allowCredentials(true);
            }
            
            
        };
    }
}
