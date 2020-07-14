package com.sc.ribbon.user.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yuantongqin
 * description:
 * 2020/4/25
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    private Long id;
    private String name;


}
