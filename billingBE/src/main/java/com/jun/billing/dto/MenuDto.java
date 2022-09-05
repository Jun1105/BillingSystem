package com.jun.billing.dto;

import com.jun.billing.pojo.Menu;
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
