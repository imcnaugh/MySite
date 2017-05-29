import com.mcnaughton.client.spotifyModels.SpotifyClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.mcnaughton")
public class MainClass {

    @Value("${twitter.consumerKey}")
    private String consumerKey;
    @Value("${twitter.consumerSec}")
    private String consumerSec;
    @Value("${twitter.applicationKey}")
    private String applicationKey;
    @Value("${twitter.applicationSec}")
    private String applicationSec;

    public static void main(String[] args){
        SpringApplication.run(MainClass.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }

    @Bean
    public Docket swaggerSettings() {
        ApiInfo apiInfo = new ApiInfo("Ian McNaughton", "Why make a templated website attempting to showcase my skills, when I can just showcase my skills. Welcome, what you are looking at is a swagger documentation of my api. Here you can get the normal array of information about myself all in JSON format!", "1.0", "", "", "", "");


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo);
    }

    @Bean
    public ConfigurationBuilder getConfigurationBuilder(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSec);
        cb.setOAuthAccessToken(applicationKey);
        cb.setOAuthAccessTokenSecret(applicationSec);
        return cb;
    }

    @Bean
    public Twitter getTwitterClient(ConfigurationBuilder cb){
        return new TwitterFactory(cb.build()).getInstance();
    }

    @Bean
    public SpotifyClientConfig getSpotifyClientConfigs(){
        return new SpotifyClientConfig();
    }
}
