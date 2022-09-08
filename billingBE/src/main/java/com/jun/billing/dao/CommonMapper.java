package com.jun.billing.dao;

import com.jun.billing.entity.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommonMapper {
    @Select("select id, name from type")
    List<Type> getType();
}
