package io.zerogone.web;

import io.zerogone.domain.LoginRequestForm;
import io.zerogone.domain.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes(names = {"user", "visitor"})
public class LoginController {
    private final UserDao userDao;

    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("login")
    public String doLogin(@ModelAttribute @Valid LoginRequestForm loginRequestForm, Model model) {
        Optional<User> optionalUser = userDao.findByEmail(loginRequestForm.getEmail());
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "redirect:/mypage";
        } else {
            model.addAttribute("visitor", loginRequestForm);
            return "redirect:/signup";
        }
    }
}
