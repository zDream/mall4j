package com.yami.shop.common.response;

import lombok.Data;

/**
 * @Author: api.kuaidi100.com
 * @Date: 2020-07-14 16:13
 */
@Data
public class QueryTrackData {
    /**
     * 时间，原始格式
     */
    private String time;
    /**
     * 物流轨迹节点内容
     */
    private String context;
    /**
     * 格式化后时间
     */
    private String ftime;
    /**
     * 行政区域的编码
     */
    private String areaCode;
    /**
     * 行政区域的名称
     */
    private String areaName;
    /**
     * 签收状态 (0在途，1揽收，2疑难，3签收，4退签，5派件，6退回，7转投)
     */
    private String status;
}
