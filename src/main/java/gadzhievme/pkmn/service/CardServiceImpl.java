package gadzhievme.pkmn.service;

import gadzhievme.pkmn.dao.CardDao;
import gadzhievme.pkmn.dao.StudentDao;
import gadzhievme.pkmn.entities.CardEntity;
import gadzhievme.pkmn.entities.StudentEntity;
import gadzhievme.pkmn.models.Card;
import gadzhievme.pkmn.models.CardInfoResponse;
import gadzhievme.pkmn.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{
    private final CardDao cardDao;
    private final StudentDao studentDao;
    private final PokemonTcgService tcg;

    @Override
    public CardInfoResponse getCardById(UUID id) {
        Card card = cardDao.getCardById(id);
        return CardInfoResponse.fromCard(cardDao.getCardById(id), tcg.getCardImageUrl(card.getName(), card.getCard_number()));
    };

    @Override
    public Card getCardByName(String name) {
        return cardDao.getCardByName(name);
    };

    @Override
    public Card getCardByOwnerName(String familyName, String firstName, String patronicName) {
        UUID id = studentDao.getStudentIdByName(familyName, firstName, patronicName);
        return cardDao.getCardByPokemon_owner_id(id);
    };
    @Override
    public Card save(Card card) {
        if (cardDao.cardExists(card)) {
            throw new IllegalArgumentException("Card is already exist error");
        }

        return cardDao.save(card);
    }

    @Override
    public List<Card> getAll() {
        return cardDao.getAll();
    }

    @Override
    public Card getCardByOwner(Student owner) {
        UUID id = studentDao.getStudentId(owner.getSurName(), owner.getFirstName(), owner.getFamilyName(), owner.getGroup());
        return cardDao.getCardByPokemon_owner_id(id);
    };
}
