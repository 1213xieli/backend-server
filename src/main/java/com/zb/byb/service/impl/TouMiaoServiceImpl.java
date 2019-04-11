package com.zb.byb.service.impl;

import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.TouMiaoService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：谢李
 */
@Service
public class TouMiaoServiceImpl implements TouMiaoService {
    @Override
    public String queryListByUser(String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PIGINGAPPLY);
        return jsonStr;
    }

    @Override
    public boolean saveInfo(TouMiao info) throws Exception {
        String data= JSONObject.fromObject(info).toString();
        BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PIGINGAPPLY);
        return true;
    }
}
