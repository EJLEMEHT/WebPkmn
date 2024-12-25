package gadzhievme.pkmn.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gadzhievme.pkmn.models.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;
@Entity
@Table(name = "cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardEntity {
    @Id
    private UUID id;
    private String name;
    private int hp;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "evolves_from_id")
    private CardEntity evolvesFrom;
    private String game_set;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
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
                    .card_number(card.getCard_number());
                    if (card.getEvolvesFrom() != null) {
                        cardEntityBuilder.evolvesFrom(CardEntity.fromDTO(card.getEvolvesFrom()));
                    }
                    if (card.getResistanceType() != null) {
                        cardEntityBuilder.resistance_type(card.getResistanceType().toString());
                    }
                    if (card.getPokemonOwner() != null) {
                        cardEntityBuilder.pokemonOwner(StudentEntity.fromDTO(card.getPokemonOwner()));
                    }
            return cardEntityBuilder.build();
    }
}
