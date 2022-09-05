package com.jun.billing.pojo;

import lombok.Data;


@Data
public class Menu {
    private int id;
    private int pid;
    private String title;
    private String menu_name;
    private String url;

}
