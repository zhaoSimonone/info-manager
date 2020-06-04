package school.info.manage.infomanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import school.info.manage.infomanager.dto.InfoDTO;
import school.info.manage.infomanager.service.InfoService;

import java.util.List;

/**
 * 展示发布的信息的详情
 */
@Controller
public class InfoController {

    @Autowired
    InfoService infoService;

    @GetMapping("/info/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model) {
        InfoDTO infoDTO = infoService.getByInfoId(id);
        model.addAttribute("infoDTO", infoDTO);
        return "info";
    }
}
