package flow.uploader.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = {"flow.uploader.persistence"})
@EnableJpaRepositories(basePackages = {"flow.uploader.persistence"})
public class DBConfig {
}
