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
@TableName("tz_prod_integral")
public class Integral implements Serializable {
    @TableId
    private Long id;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 分值
     */
    private Integer score;
    private String userId;
}
