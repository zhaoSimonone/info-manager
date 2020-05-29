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
import school.info.manage.infomanager.model.User;
import school.info.manage.infomanager.provider.GithubProvider;

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
                         @RequestParam(name = "state") String state) {

    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_id(clientId);
    accessTokenDTO.setClient_secret(clientSecret);
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_uri(redirectUri);
    accessTokenDTO.setState(state);
    String accessToken = githubProvider.getAccessToken(accessTokenDTO);
    GithubUser githubUser = githubProvider.getUser(accessToken);
    String userName = githubUser.getName();
    //拿到github账户的user信息
    log.info("githubUserName: " + userName);
    log.info("githubUserID: " + githubUser.getId());

    if(userName != null) {
      User user = new User();
      //随机生成一个UUID
      user.setToken(UUID.randomUUID().toString());
      user.setName(githubUser.getName());
      user.setAccountId(String.valueOf(githubUser.getId()));
      //创建时间
      user.setGmtCreate(System.currentTimeMillis());
      //修改时间
      user.setGmtModified(user.getGmtCreate());
      userMapper.insert(user);
      return "redirect:/";
    }
    //返回index界面
    return "index";
  }
}
