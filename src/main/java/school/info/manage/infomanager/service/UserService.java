package school.info.manage.infomanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.info.manage.infomanager.dto.GiteeUser;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.User;
import school.info.manage.infomanager.model.UserExample;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  public User createOrUpdate(GiteeUser giteeUser) {
    if (giteeUser == null || giteeUser.getId() == null) {
      return null;
    }
    //select * from user where account_id = #{accountId}
    UserExample userExample = new UserExample();
    userExample.createCriteria()
            .andAccountIdEqualTo(String.valueOf(giteeUser.getId()));
    List<User> users = userMapper.selectByExample(userExample);
    User dbUser = new User();
    if (users.size() == 0) {
      //新建
      if (giteeUser.getName() == null) {
        dbUser.setName("Default");
      } else {
        dbUser.setName(giteeUser.getName());
      }
      String token = UUID.randomUUID().toString();
      dbUser.setToken(token);
      dbUser.setAccountId(String.valueOf(giteeUser.getId()));
      dbUser.setGmtCreate(System.currentTimeMillis());
      dbUser.setGmtModified(dbUser.getGmtCreate());
      dbUser.setAvatarUrl(giteeUser.getAvatarUrl());
      userMapper.insert(dbUser);
    } else {
      //更新
      dbUser = users.get(0);

      //将需要更新的内容封装到对象中
      //TODO 这一步更新操作由于不熟悉MyBatis所以比较晦涩
      User updateUser = new User();
      updateUser.setAvatarUrl(giteeUser.getAvatarUrl());
      updateUser.setName(giteeUser.getName());
      updateUser.setGmtModified(System.currentTimeMillis());

      UserExample example = new UserExample();
      example.createCriteria()
              .andIdEqualTo(dbUser.getId());
      userMapper.updateByExampleSelective(updateUser,example);
    }
    return dbUser;
  }
}

