package school.info.manage.infomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.info.manage.infomanager.mapper.InfoMapper;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.Info;
import school.info.manage.infomanager.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

  @Autowired
  private InfoMapper infoMapper;

  @Autowired
  private UserMapper userMapper;

  //发起get请求返回页面
  @GetMapping("/publish")
  public String publish() {
    return "publish";
  }

  //发起post请求处理form提交的数据
  @PostMapping("/publish")
  public String doPublish(
          @RequestParam("title") String title,
          @RequestParam("description") String description,
          @RequestParam("tag") String tag,
          HttpServletRequest request,
          Model model) {

    //先将title，description，tag对应的信息保存到model中，如果用户输入错误，则重新返回publish界面，然后用户修改以后登录
    model.addAttribute("title", title);
    model.addAttribute("description", description);
    model.addAttribute("tag", tag);

    if (title == null || title == "") {
      model.addAttribute("error", "标题不能为空");
      return "publish";
    }
    if (description == null || description == "") {
      model.addAttribute("error", "内容不能为空");
      return "publish";
    }
    if (tag == null || tag == "") {
      model.addAttribute("error", "标签不能为空");
      return "publish";
    }

    User user = null;
    //从cookie中得到token，然后使用这个token区数据库中查找，看是否能找到对应的用户
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("token".equals(cookie.getName())) {
          String token = cookie.getValue();
          //然后从数据库中去寻找，是否存在这个token，有的话则返回这个token对应的user
          user = userMapper.findUserByToken(token);
          //如果有这个用户，则将其写入session中
          if (user != null) {
            request.getSession().setAttribute("user", user);
          }
          break;
        }
      }
    }
    if (user == null) {
      model.addAttribute("error", "用户未登录");
      return "publish";
    }
    Info info = Info.builder()
            .creator(user.getId())
            .title(title)
            .description(description)
            .gmtCreate(System.currentTimeMillis())
            .gmtModified(System.currentTimeMillis())
            .tag(tag).build();
    infoMapper.createInfo(info);
    return "redirect:/";
  }
}
