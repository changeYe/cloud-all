package com.sc.server.order.service.impl;

import com.sc.server.order.feign.MemberInfoRemote;
import com.sc.server.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuantongqin
 * desc:
 * 2020-05-24
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    MemberInfoRemote memberInfoRemote;

    @Override
    public String getOrder(Long id) {

        log.info("先记录一些操作日志"+id);
        String name = memberInfoRemote.getName(id);
        log.info("返回结果："+name);
        return "order-server 返回结果："+name;
    }


}
