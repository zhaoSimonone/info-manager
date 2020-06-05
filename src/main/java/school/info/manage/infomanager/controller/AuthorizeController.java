package school.info.manage.infomanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.info.manage.infomanager.dto.GiteeAccessTokenDTO;
import school.info.manage.infomanager.dto.GiteeUser;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.User;
import school.info.manage.infomanager.provider.GiteeProvider;
import school.info.manage.infomanager.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class AuthorizeController {

//  @Autowired
//  private GithubProvider githubProvider;
//
//  @Value("${github.client.id}")
//  private String clientId;
//
//  //${key}从applicaltion.properties中获取值
//  @Value("${github.client.secret}")
//  private String clientSecret;
//
//  @Value("${github.redirect.uri}")
//  private String redirectUri;

  @Autowired
  private GiteeProvider giteeProvider;

  @Value("1007b2598d3da26776d554011c1598c42df38200a3270e50bb71ff005e6b58e3")
  private String clientId;

  //${key}从applicaltion.properties中获取值
  @Value("6ed10160a596b983404e3c61eaa1dd25d97c299ba0b0579ac6f2715d253ce238")
  private String clientSecret;

  @Value("http://localhost:8887/oauth/callback/gitee")
  private String redirectUri;

  @Autowired
  private UserService userService;

  @GetMapping("/oauth/callback/gitee")
  public String callBack(@RequestParam(name = "code") String code,
                         @RequestParam(name = "state") String state,
                         HttpServletRequest request,
                         HttpServletResponse response) {

    GiteeAccessTokenDTO giteeAccessTokenDTO = new GiteeAccessTokenDTO(clientId, clientSecret, code, redirectUri, state);
    String accessToken = giteeProvider.getAccessToken(giteeAccessTokenDTO);
    GiteeUser giteeUser = giteeProvider.getUser(accessToken);
    log.info("gitee: " + giteeUser.toString());

    //Todo UserService种的userMapper无法自动注入，只能像这样手动传入
    User user = userService.createOrUpdate(giteeUser);
    if (user == null) {
      //返回index界面
      return "index";
    } else {
      //从request中获取session，并将"token"放入session中
      request.getSession().setAttribute("user", user);
      //add cookie到response中
      response.addCookie(new Cookie("token", user.getToken()));
      return "redirect:/";
    }
  }
}
