package pokemon_battle_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pokemon_battle_api.entity.PokemonType;
import pokemon_battle_api.entity.Trainer;

@Service
@AllArgsConstructor
public class TrainerPokemonService {

    PokemonTypeService pokemonTypeService;
    TrainerService trainerService;

    public Trainer getTrainers(String name) {
        Trainer trainer = this.trainerService.getTrainers(name);
        trainer.getTeam().forEach(pokemon -> {
            PokemonType pokemonType = pokemonTypeService.pokemon(pokemon.getPokemonTypeId());
            pokemon.setPokemonType(pokemonType);
        });
        return trainer;
    }

}