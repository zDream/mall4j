package com.yami.shop.bean.app.param;

import lombok.Data;

/**
 * @Author : AnDi
 * @CreateTime : 2020-10-12
 * @Description :
 **/
@Data
public class IntegralParam {
    private Long orderId;
    /**
     * 1：购买商品
     * 2：签到
     * 3：邀约
     * 4：评论
     * 5：新用户注册
     */
    private Integer type;
}
