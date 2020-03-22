package pokemon_battle_api.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pokemon_battle_api.entity.Pokemon;
import pokemon_battle_api.entity.PokemonType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PokemonTypeServiceTest {
    @Test
    void listPokemonsTypes_shouldCallTheRemoteService() {
        var url = "http://localhost:8080";

        var restTemplate = mock(RestTemplate.class);
        var pokemonServiceImpl = new PokemonTypeService();
        pokemonServiceImpl.setRestTemplate(restTemplate);
        pokemonServiceImpl.setPokemonTypeServiceUrl(url);

        var pikachu = new PokemonType();
        pikachu.setName("pikachu");
        pikachu.setId(25);

        var expectedUrl = "http://localhost:8080/pokemon-types/1";
        when(restTemplate.exchange(expectedUrl, HttpMethod.GET, pokemonServiceImpl.getHttpEntity(), PokemonType.class)).thenReturn(new ResponseEntity<>(pikachu, HttpStatus.ACCEPTED));

        var pokemon = pokemonServiceImpl.pokemon(1);

        assertNotNull(pokemon);
        verify(restTemplate).exchange(expectedUrl, HttpMethod.GET, pokemonServiceImpl.getHttpEntity(), PokemonType.class);
    }
}
