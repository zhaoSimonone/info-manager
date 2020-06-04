package school.info.manage.infomanager.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import school.info.manage.infomanager.mapper.UserMapper;
import school.info.manage.infomanager.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 为登录用户添加cookie
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

  @Autowired
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
          User user = userMapper.findUserByToken(token);
          //如果有这个用户，则将其写入session中
          if (user != null) {
            request.getSession().setAttribute("user", user);
          }
          break;
        }
      }
    }
    return true;
  }
}
