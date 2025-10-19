package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/poly")
public class TrangchuController {

    @GetMapping("/trangchu")
    public String home(Model model) {
        // Gán MSSV và họ tên của bạn ở đây

        return "trangchu"; // trả về index.html
    }
}
