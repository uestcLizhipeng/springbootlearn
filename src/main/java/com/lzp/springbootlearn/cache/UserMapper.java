package com.lzp.springbootlearn.cache;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@Mapper
@CacheConfig(cacheNames = "users")
public interface UserMapper {
    @Insert("insert into user(name,age) values(#{name},#{age})")
    int addUser(@Param("name") String name,@Param("age") String age);

    @Select("select * from user where id = #{id}")
    @Cacheable(key = "#p0")
    User findById(@Param("id")String id);

    @CachePut(key = "#p0")
    @Update("update user set name=#{name} where id=#{id}")
    void updateById(@Param("id")String id,@Param("name")String name);

    @CacheEvict(key = "#p0",allEntries = true)
    @Delete("delete from user where id=#{id}")
    void  deleteById(@Param("id") String id);

}
