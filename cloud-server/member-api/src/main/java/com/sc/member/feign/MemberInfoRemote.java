package com.sc.member.feign;

import com.sc.member.hystrix.MemberInfoHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yuantongqin
 * desc: 获取会员信息
 * 2020-05-24
 */
@FeignClient(value = "member-server",fallback = MemberInfoHystrix.class)
public interface MemberInfoRemote {

    @GetMapping(value = "/getName/{id}", produces="application/json;charset=UTF-8")
    String getName(@PathVariable("id") Long Id);
}
