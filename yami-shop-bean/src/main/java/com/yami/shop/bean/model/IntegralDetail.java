package com.yami.shop.bean.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : AnDi
 * @CreateTime : 2020-10-12
 * @Description :
 **/
@Data
@TableName("tz_prod_integral_detail")
public class IntegralDetail implements Serializable {

    @TableId
    private Long id;

    private Long integralId;
    private Integer score;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 分值来源1:购买商品，2：签到，3：邀约，4：评论 5：新用户注册
     */
    private Integer source;
    private Long orderId;
    private String userId;
}
