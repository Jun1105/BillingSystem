package com.jun.billing.service.impl;

import com.jun.billing.dao.CommonMapper;
import com.jun.billing.entity.pojo.Role;
import com.jun.billing.entity.pojo.Type;
import com.jun.billing.service.CommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Resource
    CommonMapper commonMapper;
    @Override
    public List<Type> getTypeList() {
        return commonMapper.getType();
    }

    @Override
    public Boolean addTypeName(Type typeO) {
        if(typeO.getName() == null || typeO.getId() != null){
            return false;
        }
        commonMapper.addTypeName(typeO);
        return true;
    }

    @Override
    public Boolean updateTypeName(Type typeO) {
        if(typeO.getName() == null || typeO.getId() == null){
            return false;
        }
        commonMapper.updateTypeName(typeO);
        return true;
    }

    @Override
    public List<Role> getRole() {
        return commonMapper.getRole();
    }
}
