package school.info.manage.infomanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiteeAccessTokenDTO {
  private String client_id;
  private String client_secret;
  private String code;
  private String redirect_uri;
  private String state;
  private String grant_type = "authorization_code";

    public GiteeAccessTokenDTO(String client_id, String client_secret, String code, String redirect_uri, String state) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_uri = redirect_uri;
        this.state = state;
    }
}
