package school.info.manage.infomanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 建立于数据库表进行一一对应的映射:ORM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private Integer id;
  private String name;
  private String accountId;
  private String token;
  private Long gmtCreate;
  private Long gmtModified;
  private String avatarUrl;
}
