package com.jun.billing.dao;

import com.jun.billing.entity.pojo.Role;
import com.jun.billing.entity.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommonMapper {
    @Select("select id, name from type")
    List<Type> getType();

    @Insert("insert into type(name) values(#{req.name})")
    void addTypeName(@Param("req") Type typeO);

    @Update("update type set type.name = #{req.name} where type.id = #{req.id}")
    void updateTypeName(@Param("req") Type typeO);

    @Select("select id, role_name from role")
    List<Role> getRole();
}
