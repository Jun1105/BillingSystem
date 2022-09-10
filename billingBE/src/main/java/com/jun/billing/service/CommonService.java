package com.jun.billing.service;

import com.jun.billing.entity.pojo.Type;

import java.util.List;

public interface CommonService {
    List<Type> getTypeList();

    Boolean addTypeName(Type typeO);

    Boolean updateTypeName(Type typeO);
}
