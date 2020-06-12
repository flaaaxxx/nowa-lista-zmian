package pl.flaaaxxx.nowalistazmian;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.flaaaxxx.nowalistazmian.dao.model.Change;
import pl.flaaaxxx.nowalistazmian.dao.model.Item;
import pl.flaaaxxx.nowalistazmian.services.ItemService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories
@CrossOrigin(origins = "#{'${network.address}'}")
public class NowaListaZmianApplication {
    public static void main(String[] args) {
        SpringApplication.run(NowaListaZmianApplication.class, args);
    }
}
