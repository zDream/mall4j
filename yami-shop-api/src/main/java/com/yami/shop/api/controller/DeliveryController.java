/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.yami.shop.api.controller;

import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.yami.shop.bean.app.dto.DeliveryDto;
import com.yami.shop.bean.app.dto.DeliveryInfoDto;
import com.yami.shop.bean.model.Delivery;
import com.yami.shop.bean.model.Order;
import com.yami.shop.common.bean.Kuaidi;
import com.yami.shop.common.response.QueryTrackData;
import com.yami.shop.common.response.QueryTrackParam;
import com.yami.shop.common.response.QueryTrackReq;
import com.yami.shop.common.response.QueryTrackResp;
import com.yami.shop.common.util.Json;
import com.yami.shop.common.util.SignUtils;
import com.yami.shop.common.util.TrackUtils;
import com.yami.shop.service.DeliveryService;
import com.yami.shop.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/delivery")
@Api(tags="查看物流接口")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private Kuaidi kuaidi;

    /**
     * 查看物流接口
     */
    @GetMapping("/check")
    @ApiOperation(value="查看物流", notes="根据订单号查看物流")
    @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String")
    public ResponseEntity<DeliveryDto> checkDelivery(String orderNumber) {
    	Order order = orderService.getOrderByOrderNumber(orderNumber);
    	Delivery delivery = deliveryService.getById(order.getDvyId());

		QueryTrackParam queryTrackParam = new QueryTrackParam();
		queryTrackParam.setCom(delivery.getDvyCode());
		queryTrackParam.setNum(order.getDvyFlowId());

		QueryTrackReq queryTrackReq = new QueryTrackReq();
		queryTrackReq.setParam(queryTrackParam);
		queryTrackReq.setCustomer(kuaidi.getCustomer());
		queryTrackReq.setSign(SignUtils.sign(new Gson().toJson(queryTrackParam)+kuaidi.getKey()+kuaidi.getCustomer()));

		QueryTrackResp queryTrackResp = TrackUtils.queryTrack(queryTrackReq);


		DeliveryDto deliveryDto = new DeliveryDto();
		deliveryDto.setDvyFlowId(order.getDvyFlowId());
		deliveryDto.setCompanyHomeUrl(delivery.getCompanyHomeUrl());
		deliveryDto.setCompanyName(delivery.getDvyName());

		if(queryTrackResp != null){
			List<DeliveryInfoDto> dtoList = new LinkedList<>();
			List<QueryTrackData> data = queryTrackResp.getData();
			for (QueryTrackData trackData : data){
				DeliveryInfoDto deliveryInfoDto = new DeliveryInfoDto();
				deliveryInfoDto.setContext(trackData.getContext());
				deliveryInfoDto.setTime(trackData.getTime());
				dtoList.add(deliveryInfoDto);
			}
			deliveryDto.setData(dtoList);
		}

		return ResponseEntity.ok(deliveryDto);
    }
}
