package academy.digitallab.store.service_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableEurekaClient en versiones recientes no es necesario solo con agregar la dependencia de Eureka Client es suficiente
@SpringBootApplication
public class ServiceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProductApplication.class, args);
	}

}
