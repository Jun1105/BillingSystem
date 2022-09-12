package com.jun.billing.entity.pojo;

import lombok.Data;


@Data
public class Menu {
    private Integer id;
    private Integer pid;
    private String title;
    private String menu_name;
    private String url;

}
