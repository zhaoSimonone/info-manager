package school.info.manage.infomanager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.info.manage.infomanager.dto.InfoDTO;
import school.info.manage.infomanager.mapper.InfoMapper;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.Info;
import school.info.manage.infomanager.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 用来组装InfoMapper和UserMapper
 */
@Service
@Slf4j
public class InfoService {

  @Autowired
  private InfoMapper infoMapper;

  @Autowired
  private UserMapper userMapper;

  public List<InfoDTO> list() {
    List<InfoDTO> infoDTOList = new ArrayList<>();
    //查到表中所有的Info
    List<Info> infoList = infoMapper.list();
    for (Info info : infoList) {
      User user = userMapper.findUserById(info.getCreator());
      log.info("user: " + user.toString());
      InfoDTO infoDTO = new InfoDTO();
      //TODO Spring的工具类，将info中的所有属性值拷贝到infoDTO中
      BeanUtils.copyProperties(info, infoDTO);
      infoDTO.setUser(user);
      infoDTOList.add(infoDTO);
      log.info("infoDTO: " + infoDTO.toString());
    }
    return infoDTOList;
  }
}
