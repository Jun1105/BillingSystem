package com.jun.billing.service;

import com.jun.billing.entity.dto.MenuDto;

import java.util.List;


public interface MenuService {
    List<MenuDto> getMenu(Integer userId);
}
