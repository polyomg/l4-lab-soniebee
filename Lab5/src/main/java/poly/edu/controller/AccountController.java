package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import poly.edu.dao.AccountDAO;
import poly.edu.model.Account;
import poly.edu.service.CookieService;
import poly.edu.service.ParamService;
import poly.edu.service.SessionService;

@Controller
public class AccountController {

    @Autowired
    ParamService paramService;
    @Autowired
    CookieService cookieService;
    @Autowired
    SessionService sessionService;
    @Autowired
    AccountDAO accountDAO;

    @GetMapping("/account/register")
    public String registerForm() {
        return "account/register";
    }

    @PostMapping("/account/register")
    public String register(Model model, @RequestParam("photo") MultipartFile photo) {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");

        if (accountDAO.findByUsername(username) != null) {
            model.addAttribute("error", "Tên đăng nhập đã được sử dụng!");
            return "account/register";
        }

        String photoFileName = "default.png";
        if (!photo.isEmpty()) {
            paramService.save(photo, "/uploads/");
            photoFileName = photo.getOriginalFilename();
        }

        Account newAccount = new Account(username, password, photoFileName);
        accountDAO.save(newAccount);

        model.addAttribute("message", "Đăng ký thành công!");
        return "account/register";
    }

    @GetMapping("/account/login")
    public String loginForm(Model model) {
        String username = cookieService.getValue("username");
        model.addAttribute("username", username);
        return "account/login";
    }

    @PostMapping("/account/login")
    public String login(Model model) {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);

        Account user = accountDAO.findByUsername(un);

        if (user != null && user.getPassword().equals(pw)) {
            sessionService.set("username", un);
            if (rm) {
                cookieService.add("username", un, 10 * 24);
            } else {
                cookieService.remove("username");
            }
            return "redirect:/item/index";
        } else {
            model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        return "account/login";
    }
}