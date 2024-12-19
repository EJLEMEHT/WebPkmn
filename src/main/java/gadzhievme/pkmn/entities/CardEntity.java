package gadzhievme.pkmn.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gadzhievme.pkmn.models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;
@Entity
@Table(name = "cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {
    @Id
    private UUID id;
    private String name;
    private int hp;
    @OneToOne
    @JoinColumn(name = "evolves_from_id")
    private CardEntity evolvesFrom;
    private String game_set;
    @OneToOne
    @JoinColumn(name = "pokemon_owner_id")
    private StudentEntity pokemonOwner;
    private String stage;
    private String retreat_cost;
    private String weakness_type;
    private String resistance_type;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attack_skills")
    @JsonDeserialize(using = SkillDeserializer.class)
    private List<AttackSkill> skills;
    private String pokemon_type;
    private char regulation_mark;
    private String card_number;
    public static CardEntity fromDTO(Card card) {
            CardEntityBuilder cardEntityBuilder = CardEntity.builder();
                    cardEntityBuilder
                    .id(UUID.randomUUID())
                    .stage(card.getPokemon_stage().toString())
                    .name(card.getName())
                    .hp(card.getHp())
                    .pokemon_type(card.getPokemonType().toString())
                    .skills(card.getSkills())
                    .weakness_type(card.getWeaknessType().toString())
                    .retreat_cost(card.getRetreatCost())
                    .game_set(card.getGameSet())
                    .regulation_mark(card.getRegulationMark())
                    .pokemonOwner(StudentEntity.fromDTO(card.getPokemonOwner()))
                    .card_number(card.getCard_number());
                    if (card.getEvolvesFrom() != null) {
                        cardEntityBuilder.evolvesFrom(CardEntity.fromDTO(card.getEvolvesFrom()));
                    }
                    if (card.getResistanceType() != null) {
                        cardEntityBuilder.resistance_type(card.getResistanceType().toString());
                    }
            return cardEntityBuilder.build();
    }
}
