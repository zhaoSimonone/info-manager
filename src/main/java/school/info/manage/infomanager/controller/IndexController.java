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
                      @RequestParam(name = "size", defaultValue = "4") Integer size) {

    //一个Info表关联着一个creator，也就是user，service的目的就是来组装这两张表
    //InfoDTO不仅包含着Info的信息，还包含着user的信息
    PageDTO pageDTO = infoService.list(page,size);
    model.addAttribute("pageDTO", pageDTO );
    //用户发起请求访问index页面的时候，如果携带了token，那么
    return "index";
  }
}
