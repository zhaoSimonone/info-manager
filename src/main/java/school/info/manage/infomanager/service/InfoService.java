package school.info.manage.infomanager.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.info.manage.infomanager.dto.InfoDTO;
import school.info.manage.infomanager.dto.PageDTO;
import school.info.manage.infomanager.mapper.InfoMapper;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.Info;
import school.info.manage.infomanager.model.InfoExample;
import school.info.manage.infomanager.model.User;
import school.info.manage.infomanager.model.UserExample;

import java.util.ArrayList;
import java.util.List;

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


  public PageDTO list(Integer currPage, Integer size) {
    //使用MySQL的limit语句来实现查询功能，语法格式select * from info limit 5, 5;
    // 第一个参数则是起点，也就是初始下标，即从哪个下标开始读取数据。第二个参数为一次读取的条数，为5则表示读取五条，整个语句表达的含义为从下标0开始读取五条记录

    PageDTO pageDTO = new PageDTO();

    //得到数据库中的总的info的记录的数量
//    Integer totalCount = infoMapper.count();
    //    Integer totalCount = infoMapper.countById(userId);
//    InfoExample infoExample = new InfoExample();
//    infoExample.createCriteria();
    Integer totalCount = (int)infoMapper.countByExample(new InfoExample());
//    Integer totalCount = longTotalCount.intValue();

    //总的记录数为0，还需要进行后面的步骤吗？
    if (totalCount == 0) {
      pageDTO.setPageWhileCountEqualsZero();
      return pageDTO;
    }
    //设置pageDTO的分页
    pageDTO.setPageValues(totalCount, currPage, size);

    Integer startIndex = (pageDTO.getCurrentPage() - 1) * size;

    List<InfoDTO> infoDTOList = new ArrayList<>();

//    //查到表中所有的Info
//    List<Info> infoList = infoMapper.list(startIndex, size);
    InfoExample example = new  InfoExample();
//    example.createCriteria();
    List<Info> infoList = infoMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(startIndex, size));

    for (Info info : infoList) {
      log.info("description: " + info.getDescription());
      UserExample userExample = new UserExample();
      userExample.createCriteria().andIdEqualTo(info.getCreator());
//      User user = userMapper.findUserById(info.getCreator());
      List<User> users = userMapper.selectByExample(userExample);
      User user = users.get(0);
      log.info("user: " + user.toString());
      InfoDTO infoDTO = new InfoDTO();
      //TODO Spring的工具类，将info中的所有属性值一一对应的拷贝到infoDTO中
      BeanUtils.copyProperties(info, infoDTO);
      infoDTO.setUser(user);
      infoDTOList.add(infoDTO);
      log.info("infoDTO: " + infoDTO.toString());
    }
    pageDTO.setInfoDTOList(infoDTOList);
    return pageDTO;
  }

  public PageDTO serviceListByUserId(Integer userId, Integer currPage, Integer size) {
    PageDTO pageDTO = new PageDTO();

    //得到数据库中的总的info的记录的数量
//    Integer totalCount = infoMapper.countById(userId);
    InfoExample infoExample = new InfoExample();
//    infoExample.createCriteria();
    Long longTotalCount = infoMapper.countByExample(infoExample);
    Integer totalCount = longTotalCount.intValue();

    //设置pageDTO的分页
    pageDTO.setPageValues(totalCount, currPage, size);

    Integer startIndex = (pageDTO.getCurrentPage() - 1) * size;

    List<InfoDTO> infoDTOList = new ArrayList<>();

    //查到表中所有该用户ID对应的所有Info
    InfoExample example = new  InfoExample();
    example.createCriteria()
            .andCreatorEqualTo(userId);
    List<Info> infoList = infoMapper.selectByExampleWithRowbounds(example, new RowBounds(startIndex, size));

//    List<Info> infoList = infoMapper.mapperListByUserId(userId, startIndex, size);

    for (Info info : infoList) {
//      User user = userMapper.findUserById(info.getCreator());
      UserExample userExample = new UserExample();
      userExample.createCriteria()
              .andIdEqualTo(info.getCreator());
      List<User> users = userMapper.selectByExample(userExample);
      User user = users.get(0);
      log.info("user: " + user.toString());
      InfoDTO infoDTO = new InfoDTO();
      //TODO Spring的工具类，将info中的所有属性值一一对应的拷贝到infoDTO中
      BeanUtils.copyProperties(info, infoDTO);
      infoDTO.setUser(user);
      infoDTOList.add(infoDTO);
      log.info("infoDTO: " + infoDTO.toString());
    }
    pageDTO.setInfoDTOList(infoDTOList);
    return pageDTO;
  }

  public InfoDTO getByInfoId(Integer id) {
    Info info = infoMapper.selectByPrimaryKey(id);
    InfoDTO infoDTO = new InfoDTO();

    UserExample userExample = new UserExample();
    userExample.createCriteria()
            .andIdEqualTo(info.getCreator());
    List<User> users = userMapper.selectByExample(userExample);
    User user = users.get(0);
    BeanUtils.copyProperties(info, infoDTO);
    infoDTO.setUser(user);

    return infoDTO;
  }

  public void createOrUpdate(Info info) {
    if (info.getId() == null) {
      //创建
      info.setGmtCreate(System.currentTimeMillis());
      info.setGmtModified(System.currentTimeMillis());
      info.setLikeCount(0);
      info.setViewCount(0);
      info.setCommentCount(0);
      infoMapper.insert(info);
//      infoMapper.createInfo(info);
    } else {
      //更新
      Info updateInfo = new Info();
      updateInfo.setGmtModified(System.currentTimeMillis());
//      info.setGmtModified(System.currentTimeMillis());

      User updateUser = new User();


      InfoExample example = new InfoExample();
      example.createCriteria()
              .andIdEqualTo(info.getId());
      infoMapper.updateByExampleSelective(updateInfo,example);
//      infoMapper.updateInfo(info);
    }
  }
}
