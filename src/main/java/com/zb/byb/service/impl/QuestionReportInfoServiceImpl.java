package com.zb.byb.service.impl;

import com.zb.byb.common.CommonFunc;
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
    public String saveQuestionReport(QuestionReportInfo info) throws Exception {

        Map<String, Object> map = new HashMap<>();
//        map.put("openId", Commonconst.OpenId);
        map.put("custId", info.getFcustid());
        map.put("source", Commonconst.WX_Flag);

        map.put("data", info);
        String data = JSONObject.fromObject(map).toString();
        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PROBLEMFEEDBACK);
        String id = JsonPluginsUtil.isRequestSuccessBackId(jsonBackStr);
        return id;
    }

    @Override
    public List<QuestionReportInfo> queryNormalQuestionList(String id) throws Exception {
        if (CommonFunc.checkNullOrEmpty(id))
            return new ArrayList<>();

        QuestionReportInfo queryInfo = new QuestionReportInfo();
        queryInfo.setFcustid(id);
        Map<String, Object> map = new HashMap<>();
//        map.put("openId", Commonconst.OpenId);
        map.put("custId", id);
        map.put("data", queryInfo);
        map.put("pageNumber","1");
        map.put("pageSize","1000");

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PROBLEMFEEDBACK);
        return JsonPluginsUtil.jsonToBeanList(jsonData, QuestionReportInfo.class);
    }

    @Override
    public QuestionReportInfo queryQuestionInfoById(String id) throws Exception {
        if (CommonFunc.checkNullOrEmpty(id))
            return new QuestionReportInfo();

        QuestionReportInfo queryInfo = new QuestionReportInfo();
        queryInfo.setId(id);
        Map<String, Object> map = new HashMap<>();
//        map.put("openId", Commonconst.OpenId);
//        map.put("custId", Commonconst.CustId);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_PROBLEMFEEDBACK);

        return JsonPluginsUtil.jsonToBean(jsonData, QuestionReportInfo.class);
    }

    @Override
    public boolean deleteQuestionInfoById(String id) throws Exception {
        if (CommonFunc.checkNullOrEmpty(id))
            return false;

        QuestionReportInfo queryInfo = new QuestionReportInfo();
        queryInfo.setId(id);
        Map<String, Object> map = new HashMap<>();
        map.put("openId", Commonconst.OpenId);
        map.put("custId", Commonconst.CustId);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_DELETE_PROBLEMFEEDBACK);

        return true;
    }
}
