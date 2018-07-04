package com.forte.auto.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.forte.auto.entity.ForteInterface;
import com.forte.auto.entity.ForteRunner;
import com.forte.auto.entity.User;

@Mapper
public interface InterfaceMapper {

	@Select("SELECT * FROM f_interface where f_status = 1")
	List<ForteInterface> getAll();

	@Insert("INSERT INTO f_interface(f_name,f_desc,f_url,f_method,f_parameters,f_status,f_createtime) "
			+ "VALUES( #{f_name}, #{f_desc},#{f_url},'post',#{f_parameters},'1',#{f_createtime})")
	void addInterface(ForteInterface bean);

	@Select("SELECT * FROM f_interface WHERE idf_interface = #{idf_interface}")
	ForteInterface getById(int id);

	@Insert("INSERT INTO f_running(f_interface_id,f_parameters,f_response,f_status,f_patch,f_createtime) "
			+ "VALUES(#{f_interface_id},#{f_parameters},#{f_response},'1',#{f_patch},#{f_createtime})")
	void addRunResult(ForteRunner runner);

	@Select("SELECT * FROM f_running WHERE f_patch = #{f_patch}")
	List<ForteRunner> getRunResultByPatch(String f_patch);

	@Update("UPDATE f_interface SET f_name=#{f_name},f_desc=#{f_desc},f_url=#{f_url},f_parameters=#{f_parameters},f_updatetime=#{f_updatetime} WHERE idf_interface =#{idf_interface}")
    void updateInterface(ForteInterface forteInterface);

    @Delete("DELETE FROM f_interface WHERE idf_interface =#{idf_interface}")
    void deleteInterface(int id);

}
