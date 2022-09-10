package com.jun.billing.dao;

import com.jun.billing.entity.pojo.Type;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommonMapper {
    @Select("select id, name from type")
    List<Type> getType();

    @Insert("insert into type(name) values(#{req.name})")
    void addTypeName(@Param("req") Type typeO);

    @Insert("update type set type.name = #{req.name} where type.id = #{req.id}")
    void updateTypeName(@Param("req") Type typeO);
}
