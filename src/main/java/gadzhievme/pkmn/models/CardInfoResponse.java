package gadzhievme.pkmn.models;

import gadzhievme.pkmn.entities.CardEntity;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardInfoResponse {
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
    private String imageUrl;

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
                ", imageUrl=" + imageUrl +
                '}';
    }

    public static CardInfoResponse fromCard(Card card, String imageUrl) {
        CardInfoResponse.CardInfoResponseBuilder cardInfoResponseBuilder = CardInfoResponse.builder();
        cardInfoResponseBuilder
                .pokemon_stage(card.getPokemon_stage())
                .name(card.getName())
                .hp(card.getHp())
                .pokemonType(card.getPokemonType())
                .skills(card.getSkills())
                .weaknessType(card.getWeaknessType())
                .retreatCost(card.getRetreatCost())
                .gameSet(card.getGameSet())
                .regulationMark(card.getRegulationMark())
                .pokemonOwner(card.getPokemonOwner())
                .card_number(card.getCard_number())
                .imageUrl(imageUrl);
        if (card.getEvolvesFrom() != null) {
            cardInfoResponseBuilder.evolvesFrom(card.getEvolvesFrom());
        }
        if (card.getResistanceType() != null) {
            cardInfoResponseBuilder.resistanceType(card.getResistanceType());
        }
        return cardInfoResponseBuilder.build();
    }
}
