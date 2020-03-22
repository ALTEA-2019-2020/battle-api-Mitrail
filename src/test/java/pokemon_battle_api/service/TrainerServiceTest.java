package pokemon_battle_api.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pokemon_battle_api.entity.PokemonType;
import pokemon_battle_api.entity.Trainer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {
    @Test
    void getTrainer_shouldCallTheRemoteService() {
        var url = "http://localhost:8080";

        var restTemplate = mock(RestTemplate.class);
        var trainerServiceImpl = new TrainerService();
        trainerServiceImpl.setRestTemplate(restTemplate);
        trainerServiceImpl.setTrainerApiUrl(url);

        var trainer = new Trainer();
        trainer.setName("Ash");

        var expectedUrl = "http://localhost:8080/trainers/" + trainer.getName();
        when(restTemplate.getForObject(expectedUrl, Trainer.class)).thenReturn(trainer);

        var resultTrainer = trainerServiceImpl.getTrainers(trainer.getName());

        assertNotNull(resultTrainer);
        verify(restTemplate).getForObject(expectedUrl, Trainer.class);
    }
}
