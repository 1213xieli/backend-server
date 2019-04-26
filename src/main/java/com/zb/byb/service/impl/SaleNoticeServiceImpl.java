package com.zb.byb.service.impl;

import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.SaleNoticeService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Service
public class SaleNoticeServiceImpl implements SaleNoticeService {
    @Override
    public String getSaleNotice(String custId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        map.put("custId", custId);
        map.put("source", Commonconst.WX_Flag);
        map.put("data", param);
        String data = JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_BIND_CUSTINFO);
        return jsonStr;
    }
}
