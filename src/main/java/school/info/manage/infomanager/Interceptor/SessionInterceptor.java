package school.info.manage.infomanager.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.User;
import school.info.manage.infomanager.model.UserExample;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 为登录用户添加cookie
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

  @Autowired(required = true)
  private UserMapper userMapper;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //TODO 针对用户直接访问index页面的时候，如果用户已经经过了授权且登录了，
    // 那么用户下次可以直接进行登录，若用户根本就没有进行登录，那么则没办法得到user的信息
    //cookie 从请求中获取cookid，添加cookid到response中
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("token".equals(cookie.getName())) {
          String token = cookie.getValue();
          //然后从数据库中去寻找，是否存在这个token，有的话则返回这个token对应的user

          //使用mappser生成的的查询。需要表达的sql意思为：select * from user where token = #{token}
          UserExample userExample = new UserExample();
          userExample.createCriteria()
                  .andTokenEqualTo(token);
          List<User> users = userMapper.selectByExample(userExample);
          //如果有这个用户，则将其写入session中
          if (users.size() != 0) {
            request.getSession().setAttribute("user", users.get(0));
          }
          break;
        }
      }
    }
    return true;
  }
}
