package pokemon_battle_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pokemon_battle_api.entity.Trainer;

import java.util.Objects;

@Service
public class TrainerService {
    private RestTemplate restTemplate;

    private String trainerApiUrl;

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setTrainerApiUrl(String pokemonServiceUrl) {
        this.trainerApiUrl = pokemonServiceUrl;
    }

    public Trainer getTrainers(String name) {
        return restTemplate.getForObject(trainerApiUrl + "/trainers/"+name, Trainer.class);
    }
}