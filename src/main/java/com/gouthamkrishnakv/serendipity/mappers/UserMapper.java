package com.gouthamkrishnakv.serendipity.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gouthamkrishnakv.serendipity.models.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> findAllUsers();

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findUserById(Long id);
}
