package com.zb.byb.service.impl;

import com.zb.byb.entity.FeedApply;
import com.zb.byb.entity.FeedRecord;
import com.zb.byb.service.FeedApplyService;
import com.zb.byb.service.FeedRecordService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FeedApplyServiceImpl implements FeedApplyService {

    @Override
    public String feedApply(FeedApply feedApply, String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //batchId="QOKuwU+4Q5uVQ5msWQNUVEMbbjA="
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",feedApply);//参数QUERY_
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PICKINGAPPLY);
        return JsonPluginsUtil.isRequestSuccessBackId(jsonStr);
    //{"code":"0000","id":"Va4AAAicTNbq+f3A","msg":"生成成功!"}
    }

    @Override
    public String queryFeedApply(FeedApply feedApply, String userId) throws Exception {
        return null;
    }
}
