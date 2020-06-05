package school.info.manage.infomanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
  /*
   response.body: {
       "access_token":"24eb1be0a5f70833bef8db31d396c991",
       "token_type":"bearer","expires_in":86400,
       "refresh_token":"8dfef4d5cb8448d506db8502f20b290c95a2b2c91aaabce6993643e5f41a8101",
       "scope":"user_info",
       "created_at":1591347779
   }
   */
  private String access_token;
  private String token_type;
  private Long expires_in;
  private String refresh_token;
  private String scope;
  private Long created_at;
}
