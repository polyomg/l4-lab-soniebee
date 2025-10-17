package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.bean.Staff;

import java.util.List;

@Controller
public class StaffController {

    // Bài 1: Hiển thị thông tin chi tiết của một nhân viên
    @RequestMapping("/staff/detail")
    public String detail(Model model) {
        Staff staff = Staff.builder()
                .id("user@gmail.com")
                .fullname("nguyễn văn user")
                .level(2) // Cấp bậc Tướng
                .build();
        model.addAttribute("staff", staff);
        return "demo/staff-detail";
    }

    // Phương thức chung để tạo danh sách nhân viên cho các bài 2, 4, 5
    private List<Staff> getStaffList() {
        return List.of(
                Staff.builder().id("user1@gmail.com").fullname("nguyễn văn user1").level(0).build(),
                Staff.builder().id("user2@gmail.com").fullname("nguyễn văn user2").level(1).build(),
                Staff.builder().id("user3@gmail.com").fullname("nguyễn văn user3").level(2).build(),
                Staff.builder().id("user4@gmail.com").fullname("nguyễn văn user4").level(2).build(),
                Staff.builder().id("user5@gmail.com").fullname("nguyễn văn user5").level(1).build(),
                Staff.builder().id("user6@gmail.com").fullname("nguyễn văn user6").level(0).build()
        );
    }

    // Bài 2: Hiển thị danh sách nhân viên
    @RequestMapping("/staff/list")
    public String list(Model model) {
        model.addAttribute("list", getStaffList());
        return "demo/staff-list";
    }

    // Bài 4: Hiển thị danh sách nhân viên với thông tin trạng thái
    @RequestMapping("/staff/status")
    public String status(Model model) {
        model.addAttribute("list", getStaffList());
        return "demo/list-status";
    }

    // Bài 5: Hiển thị danh sách nhân viên trên các controls
    @RequestMapping("/staff/controls")
    public String controls(Model model) {
        model.addAttribute("list", getStaffList());
        return "demo/list-controls";
    }
}