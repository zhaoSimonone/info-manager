package school.info.manage.infomanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.info.manage.infomanager.dto.AccessTokenDTO;
import school.info.manage.infomanager.dto.GithubUser;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.Info;
import school.info.manage.infomanager.model.User;
import school.info.manage.infomanager.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@Slf4j
public class AuthorizeController {

  @Autowired
  private GithubProvider githubProvider;

  @Value("${github.client.id}")
  private String clientId;

  //${key}从applicaltion.properties中获取值
  @Value("${github.client.secret}")
  private String clientSecret;

  @Value("${github.redirect.uri}")
  private String redirectUri;

  @Autowired
  private UserMapper userMapper;

  @GetMapping("/callback")
  public String callBack(@RequestParam(name = "code") String code,
                         @RequestParam(name = "state") String state,
                         HttpServletRequest request,
                         HttpServletResponse response) {

    Info info = Info.builder().tag("hello").description("今天不错").build();
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_id(clientId);
    accessTokenDTO.setClient_secret(clientSecret);
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_uri(redirectUri);
    accessTokenDTO.setState(state);
    //TODO accessToken和githubUser可能为null
    String accessToken = githubProvider.getAccessToken(accessTokenDTO);
    GithubUser githubUser = githubProvider.getUser(accessToken);

    if (githubUser != null && githubUser.getId() != null) {
      String userName = githubUser.getName();
      //拿到github账户的user信息并打印
//      log.info("githubUserName: " + userName);
//      log.info("githubUserID: " + githubUser.getId());
      //TODO 2020-6-3-22:07

      //如果数据库中有对应的用户，则不需要再添加数据到数据库
      User findUser = userMapper.findUserByAccountId(githubUser.getId());
      if (findUser != null){
        //从request中获取session，并将"token"放入session中
        request.getSession().setAttribute("user", findUser);

        //add cookie到response中
        response.addCookie(new Cookie("token", findUser.getToken()));
        return "redirect:/";
      }

      User user = new User();
      //如果Github账号没有设置name，那我们就为其添加一个默认的名字
      if (githubUser.getName() == null) {
        user.setName("Default");
      } else {
        user.setName(githubUser.getName());
      }
      //随机生成一个UUID，存入数据库，然后将其作为判断其是否登录的依据
      String token = UUID.randomUUID().toString();
      user.setToken(token);

      //设置accountID,也就是用户的ID
      user.setAccountId(String.valueOf(githubUser.getId()));

      //创建时间
      user.setGmtCreate(System.currentTimeMillis());

      //修改时间
      user.setGmtModified(user.getGmtCreate());

      //用户头像的URL
      user.setAvatarUrl(githubUser.getAvatarUrl());

      //将读取的数据写入数据库
      userMapper.insert(user);

      //add cookie到response中
      response.addCookie(new Cookie("token", token));

      //从request中获取session，并将"token"放入session中
      request.getSession().setAttribute("user", user);

      return "redirect:/";
    }
    //返回index界面
    return "index";
  }
}
