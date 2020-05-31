package school.info.manage.infomanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.info.manage.infomanager.model.User;

/**
 * DTO为传输层模型
 * 信息基本上与model中的Info保持一致，Model是数据库模型，用来和数据库中的字段一一对应，在Info这个表中，我们记录了创建者的ID，
 * 然后我们还需要通过用户的ID，拿到用户的其它信息，需要将两张表关联在一起，所以增加了user这个属性
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoDTO {
  private Integer id;
  private Integer creator;
  private String title;
  private String description;
  private Long gmtCreate;
  private Long gmtModified;
  private Integer viewCount;
  private Integer commentCount;
  private Integer likeCount;
  private String tag;
  private User user;
}
