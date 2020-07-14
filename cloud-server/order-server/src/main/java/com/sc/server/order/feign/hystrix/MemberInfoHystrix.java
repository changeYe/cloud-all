package com.sc.server.order.feign.hystrix;

import com.sc.server.order.feign.MemberInfoRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * desc:
 * 2020-05-24
 */
@Component
@Slf4j
public class MemberInfoHystrix implements MemberInfoRemote {

    @Override
    public String getName(Long id) {
        log.info("member-api 服务降级："+id);
        return "获取用户name失败 : "+id;
    }
}
