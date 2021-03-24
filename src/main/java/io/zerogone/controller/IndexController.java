package io.zerogone.controller;

import io.zerogone.user.model.CurrentUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.math.BigInteger;

@Controller
public class IndexController {
    @GetMapping("/")
    public String getIndexViewName(HttpSession session, Model model) {
        CurrentUserInfo userInfo = new CurrentUserInfo();
        userInfo.setId(1);
        userInfo.setEmail("dudrhs571@gmail.com");
        userInfo.setName("김영곤");
        userInfo.setNickName("zeroGone");
        userInfo.setImgUrl("/img/user-default/1.png");
        session.setAttribute("userInfo", userInfo);

        String naverClientId = "gWo2HStBsDicQk1vH8VV";//애플리케이션 클라이언트 아이디값";
        String redirectURI = "http://localhost:8080/callback";
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String naverApiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        naverApiURL += "&client_id=" + naverClientId;
        naverApiURL += "&redirect_uri=" + redirectURI;
        naverApiURL += "&state=" + state;
        session.setAttribute("state", state);
        model.addAttribute("naverApiUrl", naverApiURL);



        return "index";
    }

}