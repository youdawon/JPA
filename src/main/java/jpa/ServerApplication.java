package jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(ServerApplication.class, args);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
