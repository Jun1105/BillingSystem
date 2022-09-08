package com.jun.billing.service.impl;

import com.jun.billing.dao.CommonMapper;
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
}
