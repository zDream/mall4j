package com.yami.shop.common.util;

import com.google.gson.Gson;
import com.yami.shop.common.response.HttpResult;
import com.yami.shop.common.response.QueryTrackReq;
import com.yami.shop.common.response.QueryTrackResp;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author : AnDi
 * @CreateTime : 2020-10-21
 * @Description :
 **/
public class TrackUtils {

    public static QueryTrackResp queryTrack(QueryTrackReq queryTrackReq) {
        QueryTrackResp queryTrackResp = new QueryTrackResp();
        if (queryTrackReq == null){
            return null;
        }

        HttpResult httpResult = HttpUtils.doPost("https://poll.kuaidi100.com/poll/query.do", queryTrackReq);

        if (httpResult.getStatus() == 200 && StringUtils.isNotBlank(httpResult.getBody())){
            queryTrackResp = new Gson().fromJson(httpResult.getBody(),QueryTrackResp.class);
        }
        return queryTrackResp;
    }

}
