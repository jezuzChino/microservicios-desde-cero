package academy.digitallab.store.service_shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableEurekaClient en versiones recientes no es necesario solo con agregar la dependencia de Eureka Client es suficiente
@SpringBootApplication
@EnableFeignClients // Habilita los clientes Feign en la aplicación
public class ServiceShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceShoppingApplication.class, args);
	}

}
