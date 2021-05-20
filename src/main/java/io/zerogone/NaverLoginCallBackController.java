package io.zerogone;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NaverLoginCallBackController {
    @GetMapping("naver_login")
    public String getNaverLoginCallBack() {
        return "naver_callback";
    }
}
