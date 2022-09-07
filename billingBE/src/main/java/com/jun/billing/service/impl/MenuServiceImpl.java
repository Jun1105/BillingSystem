package com.jun.billing.service.impl;

import com.jun.billing.dao.UserMapper;
import com.jun.billing.entity.dto.MenuDto;
import com.jun.billing.entity.pojo.Menu;
import com.jun.billing.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    public UserMapper userMapper;
    @Override
    public List<MenuDto> getMenu(int userId) {
        List<Menu> menu = userMapper.getMenu(userId);
        return getNewMenu(menu);
    }

    public List<MenuDto> getNewMenu(List<Menu> menus){
        List<MenuDto> menuList = new ArrayList<>();
        menus.stream().forEach(o-> {
            if(o.getPid() == 0){
                List<Menu> children = new ArrayList<>();
                MenuDto menuDto = new MenuDto(o.getId(),o.getTitle(),o.getMenu_name(), children);
                menuList.add(menuDto);
            }else return;
        });
        for (int i = 0;i < menuList.size();i++){
            for (int j = 0;j < menus.size();j++){
                if(menuList.get(i).getId() == menus.get(j).getPid()){
                    menuList.get(i).getChildren().add(menus.get(j));
                }
            }
        }
        return menuList;
    }
}
