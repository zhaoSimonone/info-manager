package school.info.manage.infomanager.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import school.info.manage.infomanager.model.Info;
import school.info.manage.infomanager.model.User;

import java.util.List;

@Mapper
@Component
public interface InfoMapper {

  @Insert("insert into info (creator, title, description, gmt_create, gmt_modified, tag) values (#{creator},#{title},#{description},#{gmtCreate},#{gmtModified},#{tag})")
  void createInfo(Info info);

  /**
   * 实现分页查询
   * @param startIndex
   * @param size
   * @return
   */
  @Select("select * from info limit #{startIndex}, #{size}")
  List<Info> list(@Param("startIndex") Integer startIndex, @Param("size") Integer size);

  @Select("select count(1) from info")
  Integer count();
}
