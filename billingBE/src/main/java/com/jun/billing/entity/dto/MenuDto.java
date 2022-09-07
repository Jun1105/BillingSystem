package com.jun.billing.entity.dto;

import com.jun.billing.entity.pojo.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MenuDto {
    private int id;
    private String title;
    private String menu_name;
    private List<Menu> children;
}
