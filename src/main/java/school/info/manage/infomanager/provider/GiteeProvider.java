package school.info.manage.infomanager.provider;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import school.info.manage.infomanager.dto.GiteeAccessTokenDTO;
import school.info.manage.infomanager.dto.GiteeUser;
import school.info.manage.infomanager.dto.ResponseDTO;

import java.io.IOException;

//将当前类初始化为Spring的上下文 -IOC
@Component
@Slf4j
public class GiteeProvider {

  public String getAccessToken(GiteeAccessTokenDTO giteeAccessTokenDTO) {
    MediaType mediaType = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(giteeAccessTokenDTO));
    //return "https://gitee.com/oauth/token?grant_type=authorization_code&code=" + code + "&client_id=" + GITEE_CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&client_secret=" + GITEE_CLIENT_SECRET;
    Request request = new Request.Builder()
            .url("https://gitee.com/oauth/token")
            .post(body)
            .build();
    try (Response response = client.newCall(request).execute()) {
      String str = response.body().string();
      log.info("response.body: " + str);
      ResponseDTO responseDTO = JSON.parseObject(str, ResponseDTO.class);
      String token = responseDTO.getAccess_token();
      log.info("token: " + token);
      return token;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public GiteeUser getUser(String accessToken) {
    OkHttpClient client = new OkHttpClient();
    //return "" + accessToken;
    Request request = new Request.Builder().url("https://gitee.com/api/v5/user?access_token=" + accessToken).build();
    try (Response response = client.newCall(request).execute()) {
      String str = response.body().string();
      //TODO JSON.parseObject
      //将str的JSON字符串，自动解析并转化为Java的类对象
      GiteeUser giteeUser = JSON.parseObject(str, GiteeUser.class);
      return giteeUser;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
