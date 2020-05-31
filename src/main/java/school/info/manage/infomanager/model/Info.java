package school.info.manage.infomanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Info {
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
}
