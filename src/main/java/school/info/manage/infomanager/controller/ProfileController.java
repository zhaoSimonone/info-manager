package school.info.manage.infomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.info.manage.infomanager.dto.PageDTO;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.User;
import school.info.manage.infomanager.service.InfoService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private InfoService infoService;

  //动态地跳转页面
  @GetMapping("/profile/{action}")
  public String profile(@PathVariable(name = "action") String action,
                        Model model,
                        HttpServletRequest request,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "4") Integer size) {

    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      return "redirect:/";
    }
    //如果点击的是我的发布，则跳转到我的发布
    if ("myPublish".equals(action)) {
      model.addAttribute("section", "myPublish");
      model.addAttribute("sectionName", "我的说说");
    } else if ("latestReply".equals(action)) {
      model.addAttribute("section", "latestReply");
      model.addAttribute("sectionName", "最新回复");
    }
    PageDTO pageDTO = infoService.serviceListByUserId(user.getId(), page, size);
    model.addAttribute("pageDTO", pageDTO);
    return "profile";
  }

  //动态地跳转页面
  @GetMapping("/profile")
  public String profile() {
    return "profile";
  }
}
