package gadzhievme.pkmn.controllers;

import gadzhievme.pkmn.models.Card;
import gadzhievme.pkmn.models.Student;
import gadzhievme.pkmn.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/cards")
@RestController
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

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
    public Card getCardById(@PathVariable UUID id) {
        return cardService.getCardById(id);
    }

    @PostMapping("")
    public Card saveCard(@RequestBody Card card) {
        return cardService.save(card);
    }
}
