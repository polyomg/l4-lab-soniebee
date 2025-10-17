package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/poly")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        // trả về tên view (resources/templates/hello.html)
        return "hello";
    }
}
