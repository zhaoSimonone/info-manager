package school.info.manage.infomanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.info.manage.infomanager.dto.GiteeUser;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.User;

import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  public User createOrUpdate(GiteeUser giteeUser) {
    if (giteeUser == null || giteeUser.getId() == null) {
      return null;
    }
    User dbUser = userMapper.findUserByAccountId(String.valueOf(giteeUser.getId()));
    if (dbUser == null) {
      dbUser = new User();
      //如果Gitee账号没有设置name，那我们就为其添加一个默认的名字
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
      dbUser.setAvatarUrl(giteeUser.getAvatarUrl());
      dbUser.setName(giteeUser.getName());
      dbUser.setGmtModified(System.currentTimeMillis());
      userMapper.update(dbUser);
    }
    return dbUser;
  }
}

