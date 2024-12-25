package gadzhievme.pkmn.service;

import gadzhievme.pkmn.entities.CardEntity;
import gadzhievme.pkmn.entities.StudentEntity;
import gadzhievme.pkmn.models.Card;
import gadzhievme.pkmn.models.CardInfoResponse;
import gadzhievme.pkmn.models.Student;

import java.util.List;
import java.util.UUID;

public interface CardService {
    CardInfoResponse getCardById(UUID id);

    Card getCardByName(String name);

    Card getCardByOwnerName(String patronicName, String firstName, String familyName);

    Card getCardByOwner(Student owner);

    Card save(Card card);

    List<Card> getAll();
}
