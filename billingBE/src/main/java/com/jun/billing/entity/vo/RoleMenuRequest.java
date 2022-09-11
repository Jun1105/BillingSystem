package com.jun.billing.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenuRequest {
    private Integer roleId;
    private List<Integer> menuIdList;
}
