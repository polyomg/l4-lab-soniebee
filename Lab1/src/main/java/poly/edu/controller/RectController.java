package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rect")
public class RectController {

    @GetMapping("/form")
    public String form() {
        return "rect";
    }

    @PostMapping("/calc")
    public String calc(@RequestParam double width,
                       @RequestParam double height,
                       Model model) {

        double area = width * height;
        double perimeter = 2 * (width + height);

        String areaFormula = width + " x " + height + " = " + area;
        String perimeterFormula = "2 x (" + width + " + " + height + ") = " + perimeter;

        model.addAttribute("width", width);
        model.addAttribute("height", height);
        model.addAttribute("area", area);
        model.addAttribute("perimeter", perimeter);

        // Truyền thêm công thức ra view
        model.addAttribute("areaFormula", areaFormula);
        model.addAttribute("perimeterFormula", perimeterFormula);

        return "rect";
    }
}
