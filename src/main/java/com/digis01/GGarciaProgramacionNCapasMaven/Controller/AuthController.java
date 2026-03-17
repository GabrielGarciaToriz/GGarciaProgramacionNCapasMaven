package com.digis01.GGarciaProgramacionNCapasMaven.Controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/usuario";
        }
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/403";
    }
}

