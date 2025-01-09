package gadzhievme.pkmn.controllers;

import gadzhievme.pkmn.models.Card;
import gadzhievme.pkmn.models.CardInfoResponse;
import gadzhievme.pkmn.models.Student;
import gadzhievme.pkmn.service.CardService;
import gadzhievme.pkmn.service.PokemonTcgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/cards")
@RestController
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final PokemonTcgService tcg;

    @GetMapping("")
    public List<Card> getAllCards() {
        return cardService.getAll();
    }

    @GetMapping("/{name}")
    public Card getCard(@PathVariable String name) {
        return cardService.getCardByName(name);
    }

    @GetMapping("/owner")
    public Card getCardByOwner(@RequestBody Student owner) {
        return cardService.getCardByOwner(owner);
    }

    @GetMapping("/id/{id}")
    public CardInfoResponse getCardById(@PathVariable UUID id) {
        return cardService.getCardById(id);
    }

    @PostMapping("")
    public ResponseEntity<String> saveCard(@RequestBody Card card) {
        if (card.getPokemonOwner() == null){
            return ResponseEntity.badRequest().body("Card without owner error");
        }
        return ResponseEntity.ok(cardService.save(card).toString());
    }

    @GetMapping("/image")
    public ResponseEntity<Void> getCardImage(@RequestBody Card card) {
        try {
            String imageUrl = tcg.getCardImageUrl(card.getName(), card.getCard_number());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(imageUrl));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
