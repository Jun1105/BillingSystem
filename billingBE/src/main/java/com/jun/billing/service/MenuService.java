package com.jun.billing.service;

import com.jun.billing.dto.MenuDto;

import java.util.List;


public interface MenuService {
    List<MenuDto> getMenu(int userId);
}
