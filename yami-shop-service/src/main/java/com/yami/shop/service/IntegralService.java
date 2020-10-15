package com.yami.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.app.param.IntegralParam;
import com.yami.shop.bean.model.Integral;

/**
 * @Author : AnDi
 * @CreateTime : 2020-10-12
 * @Description :
 **/
public interface IntegralService extends IService<Integral> {

    /**
     1. 购买商品 要传订单ID 根据商品查询 此次分数
     2. 签到
     3. 邀约
     4. 评论
     5. 新用户注册
     */
    void integralManage(String userId,IntegralParam param);
}
