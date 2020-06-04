package school.info.manage.infomanager.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import school.info.manage.infomanager.model.User;

@Mapper
@Component
public interface UserMapper {

  /**
   * #{token}表示会将形参中的值取出放入查询的语句中
   * 如果是一个对象，我们则需要用.分别取出对象对应的属性
   *
   * 需要注意的是，如果是一个普通的形参，我们需要用@Param注解来进行指定，如果是一个对象，则不需要
   */
  @Select("select * from user where token = #{token}")
  User findUserByToken(@Param("token") String token);

  @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified, avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified}, #{avatarUrl})")
  void insert(User user);

  @Select("select * from user where id = #{id}")
  User findUserById(@Param("id") Integer id);


  @Select("select * from user where account_id = #{id}")
  User findUserByAccountId(@Param("id") Integer id);

}
