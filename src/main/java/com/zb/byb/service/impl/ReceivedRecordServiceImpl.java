package com.zb.byb.service.impl;

import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.ReceivedRecordService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReceivedRecordServiceImpl implements ReceivedRecordService {

    @Override
    public List getReceivedList(String custId, Object obj) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("source", Commonconst.WX_Flag);
        map.put("data", obj);
        map.put("custId",custId);
        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_PIGINGAPPLY);
        System.out.println("领用记录查看----" + jsonData);
        return JsonPluginsUtil.jsonTOList(jsonData,Object.class);
    }
}
