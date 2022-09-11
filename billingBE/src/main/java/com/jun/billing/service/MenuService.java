package com.jun.billing.service;

import com.jun.billing.entity.dto.MenuDto;
import com.jun.billing.entity.pojo.Menu;
import com.jun.billing.entity.pojo.User;

import java.util.List;


public interface MenuService {
    List<MenuDto> getAllMenu();
    List<MenuDto> getMenu(User user);
}
