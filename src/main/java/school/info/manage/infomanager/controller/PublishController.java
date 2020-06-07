package school.info.manage.infomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.info.manage.infomanager.dto.InfoDTO;
import school.info.manage.infomanager.model.Info;
import school.info.manage.infomanager.model.User;
import school.info.manage.infomanager.service.InfoService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

  @Autowired
  private InfoService infoService;

  /**
   * @param id
   * @param model
   * @return
   */
  @GetMapping("/publish/{id}")
  public String edit(@PathVariable(name = "id") Integer id,
                     Model model) {
    InfoDTO infoDTO = infoService.getByInfoId(id);
    model.addAttribute("title", infoDTO.getTitle());
    model.addAttribute("description", infoDTO.getDescription());
    model.addAttribute("tag", infoDTO.getTag());
    model.addAttribute("id", infoDTO.getId());
    return "publish";
  }

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
          @RequestParam(value = "id", required = false) Integer id,
          HttpServletRequest request,
          Model model) {

    //先将title，description，tag对应的信息保存到model中，如果用户输入错误，则重新返回publish界面，然后用户修改以后登录
    model.addAttribute("title", title);
    model.addAttribute("description", description);
    model.addAttribute("tag", tag);
    //异常处理
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

    User user = (User) request.getSession().getAttribute("user");

    if (user == null) {
      model.addAttribute("error", "用户未登录");
      return "publish";
    }

    Info info = new Info();
    info.setId(id);
    info.setCreator(user.getId());
    info.setTitle(title);
    info.setDescription(description);
    info.setTag(tag);

//
//    info.setGmtModified();
//    info.setDescription();
////            .id(id)
////            .creator(user.getId())
////            .title(title)
////            .description(description)
////            .tag(tag).build();
    infoService.createOrUpdate(info);
    return "redirect:/";
  }
}
