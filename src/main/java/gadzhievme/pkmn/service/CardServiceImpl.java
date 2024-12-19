package gadzhievme.pkmn.service;

import gadzhievme.pkmn.dao.CardDao;
import gadzhievme.pkmn.dao.StudentDao;
import gadzhievme.pkmn.entities.CardEntity;
import gadzhievme.pkmn.entities.StudentEntity;
import gadzhievme.pkmn.models.Card;
import gadzhievme.pkmn.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{
    private final CardDao cardDao;
    private final StudentDao studentDao;

    @Override
    public Card getCardById(UUID id) {
        return cardDao.getCardById(id);
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
