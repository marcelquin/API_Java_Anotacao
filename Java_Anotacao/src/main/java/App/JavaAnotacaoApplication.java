package App;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "Java_Anotações", version = "1", description = "API de gerencia de arquivos focado em Base de pesquisas de projetos"))
public class JavaAnotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAnotacaoApplication.class, args);
	}

}
