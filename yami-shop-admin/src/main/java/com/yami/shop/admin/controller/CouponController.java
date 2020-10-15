package com.yami.shop.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yami.shop.bean.model.Coupon;
import com.yami.shop.bean.model.Order;
import com.yami.shop.bean.model.Product;
import com.yami.shop.bean.param.OrderParam;
import com.yami.shop.common.util.PageParam;
import com.yami.shop.security.util.SecurityUtils;
import com.yami.shop.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : AnDi
 * @CreateTime : 2020-10-13
 * @Description :
 **/
@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;
    /**
     * 分页获取
     */
    @GetMapping("/page")
    public ResponseEntity<IPage<Coupon>> page(PageParam<Coupon> page) {
        Long shopId = SecurityUtils.getSysUser().getShopId();
        IPage<Coupon> couponIPage = couponService.page(page);
        return ResponseEntity.ok(couponIPage);
    }
}
