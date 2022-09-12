package com.jun.billing.dao;

import com.jun.billing.entity.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuMapper {
    void addMenu(@Param("req") Menu menu);
    void updateMenu(@Param("req") Menu menu);
    void deleteMenu(@Param("menuId") Integer menuId);
}
