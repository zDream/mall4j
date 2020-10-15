package com.yami.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.model.Coupon;
import com.yami.shop.dao.CouponMapper;
import com.yami.shop.service.CouponService;
import org.springframework.stereotype.Service;

/**
 * @Author : AnDi
 * @CreateTime : 2020-10-13
 * @Description :
 **/
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {
}
