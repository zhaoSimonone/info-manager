package school.info.manage.infomanager.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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

  @Select("select * from info")
  List<Info> list();
}
