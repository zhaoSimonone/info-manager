package school.info.manage.infomanager.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import school.info.manage.infomanager.model.Info;
import school.info.manage.infomanager.model.InfoExample;

public interface InfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    long countByExample(InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int deleteByExample(InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int insert(Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int insertSelective(Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    List<Info> selectByExampleWithBLOBsWithRowbounds(InfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    List<Info> selectByExampleWithBLOBs(InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    List<Info> selectByExampleWithRowbounds(InfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    List<Info> selectByExample(InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    Info selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int updateByExampleSelective(@Param("record") Info record, @Param("example") InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") Info record, @Param("example") InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int updateByExample(@Param("record") Info record, @Param("example") InfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int updateByPrimaryKeySelective(Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table info
     *
     * @mbg.generated Mon Jun 08 00:13:27 CST 2020
     */
    int updateByPrimaryKey(Info record);
}