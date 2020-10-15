package com.yami.shop.api.listener;

import com.yami.shop.bean.event.CouponOrderEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author : AnDi
 * @CreateTime : 2020-10-13
 * @Description :
 **/
@Component("defaultCouponOrderListener")
@AllArgsConstructor
public class CouponOrderListener {

    /**
     * 计算优惠券
     */
    @EventListener(CouponOrderEvent.class)
    public void defaultConfirmOrderEvent(CouponOrderEvent event) {

    }

}
