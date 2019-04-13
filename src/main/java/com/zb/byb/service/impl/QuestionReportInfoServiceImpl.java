package com.zb.byb.service.impl;

import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.NormalQuestionInfo;
import com.zb.byb.entity.QuestionReportInfo;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.service.QuestionReportInfoService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import com.zb.framework.common.entity.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：谢李
 */
@Service
public class QuestionReportInfoServiceImpl implements QuestionReportInfoService {
    @Autowired
    private MyInfoService myInfoService;
    @Override
    public void saveQuestionReport(QuestionReportInfo info) throws Exception {
        String data = JSONObject.fromObject(info).toString();
        BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PROBLEMFEEDBACK);
        return;
    }

    @Override
    public List<NormalQuestionInfo> queryNormalQuestionList(Map<String, Object> paramMap) throws Exception {
        String backData= myInfoService.viewMyInfo(Commonconst.OpenId);

//        Map<String, String> map1 = JsonPluginsUtil.jsonToMap(backData);
        JSONObject jsonObject1=JSONObject.fromObject(backData);
        JSONObject jsonObject=JSONObject.fromObject(jsonObject1.getString("data"));
        String custId=jsonObject.getString("id");

        Map<String, Object> map = new HashMap<>();
        // 传一个养户id、openid
        map.put("openId", Commonconst.OpenId);
        map.put("custId", custId);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PROBLEMFEEDBACK);
//        Map dataMap = JsonPluginsUtil.jsonToMap(jsonData);

        JSONObject jsonObj= JSONObject.fromObject(jsonData);
        String normalStr = jsonObj.getString("data");
        List<NormalQuestionInfo> result = new ArrayList<>();
        result = JsonPluginsUtil.jsonToBeanList(normalStr, NormalQuestionInfo.class);

        return result;
    }
}
