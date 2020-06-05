package school.info.manage.infomanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginOutController {

  @GetMapping("/loginOut")
  public String loginOut(HttpServletRequest request,
                         HttpServletResponse response) {
    request.getSession().removeAttribute("user");
    //清除cookie
    Cookie cookie = new Cookie("token", null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    return "redirect:/";
  }
}
