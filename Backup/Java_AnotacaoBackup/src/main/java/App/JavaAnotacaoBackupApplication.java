package App;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Micro Serviço Backup", version = "1", description = "Micro serviço responsavel pelo Backup das informações de pacotes"))
public class JavaAnotacaoBackupApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAnotacaoBackupApplication.class, args);
	}

}
