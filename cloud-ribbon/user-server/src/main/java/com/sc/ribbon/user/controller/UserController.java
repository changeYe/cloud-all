package com.sc.ribbon.user.controller;

import java.util.concurrent.TimeUnit;

import com.sc.ribbon.user.dto.CommonResult;
import com.sc.ribbon.user.dto.UserDTO;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuantongqin
 * description:
 * 2020/4/25
 */
@RestController
public class UserController {


    @GetMapping("/user")
    public CommonResult<UserDTO> getUser(@PathVariable Long id){
        CommonResult forObject = new CommonResult();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id).setName("张三" + RandomStringUtils.random(10));
        forObject.setBody(userDTO);
        return forObject;
    }

    @GetMapping("/user/name")
    public CommonResult<UserDTO> getUserByName(@RequestParam String name){
        CommonResult<UserDTO> result = new CommonResult<>();
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(name.equals("张三")){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(1L).setName(name);
            result.setBody(userDTO);
        }else{
            result.setMessage("没有此姓名的人");
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return result;
    }

}
