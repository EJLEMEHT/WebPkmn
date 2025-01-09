package gadzhievme.pkmn.models;

import gadzhievme.pkmn.entities.CardEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
@Builder
public class Card implements Serializable {
    private String card_number;
    private PokemonStage pokemon_stage;
    private String name;
    private int hp;
    private EnergyType pokemonType;
    private Card evolvesFrom;
    private List<AttackSkill> skills;
    private EnergyType weaknessType;
    private EnergyType resistanceType;
    private String retreatCost;
    private String gameSet;
    private char regulationMark;
    private Student pokemonOwner;
    public static final long serialVersionUID = 1L;

    public Card(String card_number, PokemonStage pokemonStage, String name, int hp, EnergyType pokemonType,
                Card evolvesFrom, List<AttackSkill> skills, EnergyType weaknessType,
                EnergyType resistanceType, String retreatCost, String gameSet, char regulationMark,
                Student pokemonOwner) {
        this.card_number = card_number;
        this.pokemon_stage = pokemonStage;
        this.name = name;
        this.hp = hp;
        this.pokemonType = pokemonType;
        this.evolvesFrom = evolvesFrom;
        this.skills = skills;
        this.weaknessType = weaknessType;
        this.resistanceType = resistanceType;
        this.retreatCost = retreatCost;
        this.gameSet = gameSet;
        this.regulationMark = regulationMark;
        this.pokemonOwner = pokemonOwner;
    }

    public Card() {}

    @Override
    public String toString() {
        return "Card{" +
                "pokemon_stage=" + pokemon_stage +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", pokemonType=" + pokemonType +
                ", evolvesFrom=" + evolvesFrom +
                ", skills=" + skills +
                ", weaknessType=" + weaknessType +
                ", resistanceType=" + resistanceType +
                ", retreatCost='" + retreatCost + '\'' +
                ", gameSet='" + gameSet + '\'' +
                ", regulationMark=" + regulationMark +
                ", pokemonOwner=" + pokemonOwner +
                ", card_number=" + card_number +
                '}';
    }

    public static Card fromEntity(CardEntity entity) {
        CardBuilder cardBuilder = Card.builder();
        cardBuilder
                .pokemon_stage(PokemonStage.valueOf(entity.getStage()))
                .name(entity.getName())
                .hp(entity.getHp())
                .pokemonType(EnergyType.valueOf(entity.getPokemon_type()))
                .skills(entity.getSkills())
                .weaknessType(EnergyType.valueOf(entity.getWeakness_type()))
                .retreatCost(entity.getRetreat_cost())
                .gameSet(entity.getGame_set())
                .regulationMark(entity.getRegulation_mark())
                .card_number(entity.getCard_number());
        if (entity.getEvolvesFrom() != null) {
            cardBuilder.evolvesFrom(Card.fromEntity(entity.getEvolvesFrom()));
        }
        if (entity.getResistance_type() != null) {
            cardBuilder.resistanceType(EnergyType.valueOf(entity.getResistance_type()));
        }
        if (entity.getPokemonOwner() != null) {
            cardBuilder.pokemonOwner(Student.fromEntity(entity.getPokemonOwner()));
        }
        return cardBuilder.build();
    }
}