package App.Config;


import App.ApiRest.Infra.Gateway.BackupGateway;
import App.ApiRest.Infra.UseCase.UseCaseBackupGet;
import App.ApiRest.Infra.UseCase.UseCaseBackupPost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackupConfig {
    @Bean
    UseCaseBackupGet useCaseBackupGet(BackupGateway backupGateway)
    {
        return  new UseCaseBackupGet(backupGateway);
    }
    @Bean
    UseCaseBackupPost useCaseBackupPost(BackupGateway backupGateway)
    { return new UseCaseBackupPost(backupGateway);}
}
