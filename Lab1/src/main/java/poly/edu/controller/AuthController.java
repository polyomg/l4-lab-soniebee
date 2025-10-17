package poly.edu.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class AuthController {

    @GetMapping("/form")
    public String form() {
        return "login"; // vào login.html
    }

    @PostMapping("/check")
    public String check(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Chuẩn hoá input để tránh khoảng trắng vô ý
        String u = username == null ? "" : username.trim();
        String p = password == null ? "" : password.trim();

        List<String> errors = new ArrayList<>();

        // Kiểm tra thiếu trường
        if (u.isEmpty()) {
            errors.add("Vui lòng nhập tên đăng nhập.");
        }
        if (p.isEmpty()) {
            errors.add("Vui lòng nhập mật khẩu.");
        }

        // Nếu không thiếu trường thì kiểm tra đúng/sai từng phần
        if (errors.isEmpty()) {
            if (!"poly".equals(u)) {
                errors.add("Sai tên đăng nhập.");
            }
            if (!"123".equals(p)) {
                errors.add("Sai mật khẩu.");
            }
        }

        // Gửi dữ liệu về view
        model.addAttribute("username", u); // giữ lại username đã nhập để hiển thị
        if (errors.isEmpty()) {
            model.addAttribute("message", "Đăng nhập thành công!");
        } else {
            model.addAttribute("errors", errors); // view sẽ lặp qua danh sách lỗi
        }

        return "login"; // vẫn trả về login.html để hiển thị message/errors
    }
}
