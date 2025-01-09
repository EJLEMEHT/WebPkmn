package gadzhievme.pkmn.dao;

import gadzhievme.pkmn.entities.CardEntity;
import gadzhievme.pkmn.models.Card;
import gadzhievme.pkmn.repository.CardEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CardDao {
    private final CardEntityRepository cardEntityRepository;

    @SneakyThrows
    public Card getCardById(UUID id) {
        return Card.fromEntity(cardEntityRepository.findById(id).orElseThrow(
                () -> new UserPrincipalNotFoundException("Card" +
                        "with id " + id + " not found")
        ));
    }

    @SneakyThrows
    public Card getCardByName(String name) {
        return Card.fromEntity(cardEntityRepository.findCardByName(name).orElseThrow(
                () -> new UserPrincipalNotFoundException("Card" +
                        "with name " + name + " not found")
        ));
    }
    @SneakyThrows
    public Card getCardByPokemon_owner_id(UUID id) {
        return Card.fromEntity(cardEntityRepository.findCardByPokemonOwner_id(id).orElseThrow(
                () -> new UserPrincipalNotFoundException("Card" +
                        "with owner id " + id + " not found")
        ));
    }
    @SneakyThrows
    public Card save(Card card) {
        return Card.fromEntity(cardEntityRepository.save(CardEntity.fromDTO(card)));
    }
    @SneakyThrows
    public List<Card> getAll() {
        return cardEntityRepository.findAll().stream().map(Card::fromEntity).toList();
    }

    public boolean cardExists(Card card) {
        return cardEntityRepository.existsByName(card.getName());
    }
}
