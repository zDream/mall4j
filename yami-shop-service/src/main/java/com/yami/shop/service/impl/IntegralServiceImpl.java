package com.yami.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.app.param.IntegralParam;
import com.yami.shop.bean.model.Integral;
import com.yami.shop.bean.model.IntegralDetail;
import com.yami.shop.bean.model.Order;
import com.yami.shop.dao.IntegralDetailMapper;
import com.yami.shop.dao.IntegralMapper;
import com.yami.shop.dao.OrderMapper;
import com.yami.shop.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author : AnDi
 * @CreateTime : 2020-10-12
 * @Description :
 **/
@Service
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, Integral> implements IntegralService {

    @Autowired
    private IntegralMapper integralMapper;
    @Autowired
    private IntegralDetailMapper integralDetailMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @Override
    public void integralManage(String userId,IntegralParam param) {
        /**
         * 1：购买商品
         * 2：签到
         * 3：邀约
         * 4：评论
         * 5：新用户注册
         */
        if(param.getType() == 1){
            buyShopScore(userId, param);
        }else if(param.getType() == 2){
            saveIntegral(userId, param, 50,2);
        }else if(param.getType() == 3){
            saveIntegral(userId, param, 100,3);
        }else if(param.getType() == 4){
            saveIntegral(userId, param, 50,4);
        }else if(param.getType() == 5){
            saveIntegral(userId, param, 100,5);
        }

    }

    /**
     * 购买商品获得积分
     * @param userId
     * @param param
     */
    private void buyShopScore(String userId, IntegralParam param) {
        Long orderId = param.getOrderId();
        Order order = orderMapper.selectById(orderId);
        Double actualTotal = order.getActualTotal();

        saveIntegral(userId, param, actualTotal.intValue(),1);
    }

    /**
     * 保存积分
     * @param userId
     * @param param
     * @param i
     */
    private void saveIntegral(String userId, IntegralParam param, int i,Integer source) {
        QueryWrapper<Integral> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Integral integral = integralMapper.selectOne(queryWrapper);
        if (integral == null) {
            integral = new Integral();
            integral.setCreateTime(new Date());
            integral.setScore(i);
            integral.setUserId(userId);
            integralMapper.insert(integral);
        } else {
            Integer totalScore = integral.getScore() + i;
            integral.setScore(totalScore);
            integralMapper.updateById(integral);
        }

        IntegralDetail integralDetail = new IntegralDetail();
        integralDetail.setUserId(userId);
        integralDetail.setCreateTime(new Date());
        integralDetail.setIntegralId(integral.getId());
        integralDetail.setOrderId(param.getOrderId());
        integralDetail.setScore(i);
        integralDetail.setSource(source);
        integralDetailMapper.insert(integralDetail);
    }
}
