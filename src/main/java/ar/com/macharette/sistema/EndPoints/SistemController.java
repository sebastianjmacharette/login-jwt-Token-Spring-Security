package ar.com.macharette.sistema.EndPoints;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SistemController {

    @GetMapping(value = "demo")
    public String welcome() {
        return "welcome from secure endpoint";
    }
}
