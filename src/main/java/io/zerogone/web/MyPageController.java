package io.zerogone.web;

import io.zerogone.domain.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Validated
public class MyPageController {
    private final UserDao userDao;

    public MyPageController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("mypage")
    public String getMypageViewNameWithUserAndUserBlogs(@SessionAttribute User user, Model model) {
        user = userDao.findByEmail(user.getEmail()).get();
        model.addAttribute("user", user);
        model.addAttribute("blog", user.getBlogs());
        return "mypage";
    }
}
