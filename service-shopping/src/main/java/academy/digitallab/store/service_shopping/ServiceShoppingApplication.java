package academy.digitallab.store.service_shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableEurekaClient en versiones recientes no es necesario solo con agregar la dependencia de Eureka Client es suficiente
@SpringBootApplication
public class ServiceShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceShoppingApplication.class, args);
	}

}
