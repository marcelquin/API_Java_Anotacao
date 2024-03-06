package App.Config;


import App.ApiRest.Bussness.FileServer.FileServerService;
import App.ApiRest.Infra.Gateway.PacoteGateway;
import App.ApiRest.Infra.UseCase.UseCasePacoteGet;
import App.ApiRest.Infra.UseCase.UseCasePacotePost;
import App.ApiRest.Infra.UseCase.UseCasePacotePut;
import App.ApiRest.Infra.Persistence.Repository.ArquivoRepository;
import App.ApiRest.Infra.Persistence.Repository.PacoteRepository;
import App.ApiRest.Bussness.PacoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacoteConfig {

    @Bean
    UseCasePacoteGet useCasePacoteGet(PacoteGateway pacoteGateway)
    { return new UseCasePacoteGet(pacoteGateway);}

    @Bean
    UseCasePacotePost useCasePacotePost(PacoteGateway pacoteGateway)
    { return new UseCasePacotePost(pacoteGateway);}

    @Bean
    UseCasePacotePut useCasePacotePut(PacoteGateway pacoteGateway)
    { return new UseCasePacotePut(pacoteGateway);}

    @Bean
    PacoteService pacoteService(PacoteRepository pacoteRepository, ArquivoRepository arquivoRepository,
                                FileServerService fileServerService)
    { return new PacoteService(pacoteRepository, arquivoRepository, fileServerService);}

}
