package gadzhievme.pkmn.controllers;

import gadzhievme.pkmn.models.User;
import gadzhievme.pkmn.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    @PostMapping("/success")
    public String successPage(HttpServletResponse response,
                              Authentication authentication) {
        String token = jwtService.createToken(authentication.getName(), authentication.getAuthorities()
                .stream().collect(Collectors.toUnmodifiableList()));
        response.addCookie(new Cookie("JWT", Base64.getEncoder().encodeToString(token.getBytes())));
        return "Logged In";
    }

    @GetMapping("/")
    public String home(Authentication authentication) {
        if(authentication.isAuthenticated()) {
            return authentication.getName();
        } else {
            return "Not Logged In";
        }
    }

    @PostMapping("/registration")
    public String registration(@RequestBody User user) {
        if(user.getPassword() != null && user.getUsername() != null) {
            jwtService.registerUser(user.getUsername(), user.getPassword());
            return "201. Signed In Successfully.";
        } else {
            return "400";
        }
    }
}
