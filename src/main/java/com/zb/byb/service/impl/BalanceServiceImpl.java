package com.zb.byb.service.impl;

import com.zb.byb.entity.Balance;
import com.zb.byb.entity.BalanceRecord;
import com.zb.byb.service.BalanceService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class BalanceServiceImpl implements BalanceService {
    @Override
    public String balanceApply(Balance balance, String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("openId",userId);
        map.put("data",balance);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, "结算申请");
        return jsonStr;
    }

    @Override
    public String getBalanceRecord(BalanceRecord balanceRecord, String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("data",balanceRecord);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, "获取结算记录");
        return jsonStr;
    }
}
