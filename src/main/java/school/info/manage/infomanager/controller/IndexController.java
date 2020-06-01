package school.info.manage.infomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.info.manage.infomanager.dto.InfoDTO;
import school.info.manage.infomanager.dto.PageDTO;
import school.info.manage.infomanager.mapper.InfoMapper;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.Info;
import school.info.manage.infomanager.model.User;
import school.info.manage.infomanager.service.InfoService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private InfoService infoService;

  /**
   * @param request
   * @param model
   * @param page    前端的页面传入一个参数，表示要跳转到第几页
   * @param size    表示每一页需要显示多少条记录
   * @return
   */
  @GetMapping("/")
  public String hello(HttpServletRequest request,
                      Model model,
                      @RequestParam(name = "page", defaultValue = "1") Integer page,
                      @RequestParam(name = "size", defaultValue = "2") Integer size) {
    //TODO 针对用户直接访问index页面的时候，如果用户已经经过了授权且登录了，
    // 那么用户下次可以直接进行登录，若用户根本就没有进行登录，那么则没办法得到user的信息
    //cookie 从请求中获取cookid，添加cookid到response中
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("token".equals(cookie.getName())) {
          String token = cookie.getValue();
          //然后从数据库中去寻找，是否存在这个token，有的话则返回这个token对应的user
          User user = userMapper.findUserByToken(token);
          //如果有这个用户，则将其写入session中
          if (user != null) {
            request.getSession().setAttribute("user", user);
          }
          break;
        }
      }
    }

    //一个Info表关联着一个creator，也就是user，service的目的就是来组装这两张表
    //InfoDTO不仅包含着Info的信息，还包含着user的信息
    PageDTO pageDTO = infoService.list(page,size);
    model.addAttribute("pageDTO", pageDTO );
    //用户发起请求访问index页面的时候，如果携带了token，那么
    return "index";
  }
}
