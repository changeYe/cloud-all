package com.sc.server.order.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author yuantongqin
 * desc:
 * 2020-06-04
 */
public interface HelloDTO {


    @Data
    class Param {

//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime time;

//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//        LocalDateTime timeEnd;


    }

}
