package gadzhievme.pkmn.repository;

import gadzhievme.pkmn.entities.CardEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardEntityRepository extends JpaRepository<CardEntity, UUID> {
    Optional<CardEntity> findCardByName(String name);
    Optional<CardEntity> findCardByPokemonOwner_id(UUID id);
    boolean existsByName(String name);
}
