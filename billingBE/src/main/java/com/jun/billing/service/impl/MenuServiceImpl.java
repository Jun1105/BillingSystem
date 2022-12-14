package com.jun.billing.service.impl;

import com.jun.billing.dao.MenuMapper;
import com.jun.billing.dao.UserMapper;
import com.jun.billing.entity.dto.MenuDto;
import com.jun.billing.entity.pojo.Menu;
import com.jun.billing.entity.pojo.User;
import com.jun.billing.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    public UserMapper userMapper;
    @Resource
    public MenuMapper menuMapper;

    @Override
    public List<MenuDto> getAllMenu() {
        return getNewMenu(userMapper.getAllMenu());
    }

    @Override
    public List<MenuDto> getMenu(User user) {
        if(user.getId() == null){
            return null;
        }
        List<Menu> menu = userMapper.getMenu(user.getId());
        if(menu.size() == 0){
            return null;
        }
        return getNewMenu(menu);
    }

    @Override
    public Boolean addMenu(Menu menu) {
        if(menu.getTitle() == null || menu.getId() != null){
            return false;
        }
        menuMapper.addMenu(menu);
        return true;
    }

    @Override
    public Boolean updateMenu(Menu menu) {
        if(menu.getTitle() == null || menu.getId() == null){
            return false;
        }
        menuMapper.updateMenu(menu);
        return true;
    }

    @Override
    public Boolean deleteMenu(Menu menu) {
        if(menu.getId() == null){
            return  false;
        }
        menuMapper.deleteMenu(menu.getId());
        return true;
    }

    public List<MenuDto> getNewMenu(List<Menu> menus){
        List<MenuDto> menuList = new ArrayList<>();
        menus.stream().forEach(o-> {
            if(o.getPid() == null){
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
