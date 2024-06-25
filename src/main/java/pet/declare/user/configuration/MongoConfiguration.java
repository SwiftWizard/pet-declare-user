package pet.declare.user.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "pet.declare.user.domain")
public class MongoConfiguration extends AbstractMongoClientConfiguration {


    @Value("${spring.data.mongodb.uri}")
    private String MONGO_URL;

    @Value("${spring.data.mongodb.database.name}")
    public String USER_DB;

    @Override
    protected String getDatabaseName() {
        return USER_DB;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(MONGO_URL);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}