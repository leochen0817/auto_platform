package com.forte.auto.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.forte.auto.entity.User;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM user")
	@Results({ @Result(property = "email", column = "email"), @Result(property = "name", column = "name") })
	public List<User> getAll();

	@Select("SELECT * FROM user WHERE id = #{id}")
	@Results({ @Result(property = "email", column = "email"),
			@Result(property = "name", column = "name") })
	public User getOne(int i);

	@Insert("INSERT INTO user(email,name) VALUES( #{email}, #{name})")
	public void insert(User user);

	@Update("UPDATE user SET email=#{email},name=#{name} WHERE id =#{id}")
	public void update(User user);

	@Delete("DELETE FROM user WHERE id =#{id}")
	public void delete(int id);
}
