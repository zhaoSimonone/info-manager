package school.info.manage.infomanager.provider;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import school.info.manage.infomanager.dto.AccessTokenDTO;
import school.info.manage.infomanager.dto.GithubUser;

import java.io.IOException;

//将当前类初始化为Spring的上下文 -IOC
@Component
@Slf4j
public class GithubProvider {

  public String getAccessToken(AccessTokenDTO accessTokenDTO) {
    MediaType mediaType = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
    Request request = new Request.Builder()
            .url("https://github.com/login/oauth/access_token")
            .post(body)
            .build();
    try (Response response = client.newCall(request).execute()) {

      String str = response.body().string();
      log.info("response.body: " + str);
      String token = str.split("&")[0].split("=")[1];
      log.info("token: " + token);
      return token;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public GithubUser getUser(String accessToken) {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url("https://api.github.com/user?access_token=" + accessToken).build();
    try (Response response = client.newCall(request).execute()) {
      String str = response.body().string();
      //TODO JSON.parseObject
      //将str的JSON字符串，自动解析并转化为Java的类对象
      GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
      return githubUser;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
