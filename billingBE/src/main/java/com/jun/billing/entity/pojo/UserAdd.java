package com.jun.billing.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdd {
    private Integer id;
    private Integer role_id;
    private String username;
}
