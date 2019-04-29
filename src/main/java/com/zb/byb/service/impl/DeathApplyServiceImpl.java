package com.zb.byb.service.impl;

import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.DeathApply;
import com.zb.byb.entity.FeedRecord;
import com.zb.byb.service.DeathApplyService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DeathApplyServiceImpl implements DeathApplyService  {
    @Override
    public String deathApply(DeathApply deathApply, String userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",deathApply);
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_YZRHDEATH);
        return JsonPluginsUtil.isRequestSuccessBackId(jsonStr);
    }

    @Override
    public List<DeathApply> getDeathApplyRecord(String userId,DeathApply deathApply) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId",userId);//养户id
        map.put("source","WECHAT");//微信
        map.put("data",deathApply);//参数
        String data= JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_YZRHDEATH);
        return JsonPluginsUtil.jsonToBeanList(jsonStr,DeathApply.class);

    }

    @Override
    public DeathApply getDeathApplyRecordbyId(String rcordId) throws Exception {
        if (C.checkNullOrEmpty(rcordId))
            return new DeathApply();
        Map<String, Object> map = new HashMap<>();
        map.put("source", Commonconst.WX_Flag);
        DeathApply  deathApply = new DeathApply();
        deathApply.setRcordId(rcordId);
        map.put("data", deathApply);
        String data=JSONObject.fromObject(map).toString();
        String jsonBack=BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_YZRHDEATH);
        //{"code":"0000","data":{"Curcnt":0,"applyDieCnt":0,"billStatus":"保存","billStatusIndex":"10","custid":"vKYTT1wJTV+A7XdlVyduYMyeztQ=","dieAvg":11,"dieCnt":1111111,"dieDate":"2019-04-21","freedDay":111,"imgUrl":[],"latitude":0,"longitude":0,"number":"SWJL1904230004","pigfarmerCode":"SYLWYH009","rcordId":"Va4AAAicc9tSz43W","state":1},"msg":"查询成功!"}
        return JsonPluginsUtil.jsonToBean(jsonBack, DeathApply.class);
    }

    @Override
    public String cancleDeathApply(String rcordId) throws Exception {
        if (C.checkNullOrEmpty(rcordId))
           return null;
        Map<String, Object> map = new HashMap<>();
        map.put("source", Commonconst.WX_Flag);
        DeathApply  deathApply = new DeathApply();
        deathApply.setRcordId(rcordId);
        map.put("data", deathApply);
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_DELETE_YZRHDEATH);
        return jsonStr;
    }

    @Override
    public String signerDeathApply(DeathApply deathApply) throws Exception {
        if (C.checkNullOrEmpty(deathApply)||C.checkNullOrEmpty(deathApply.getSignerList()))
            throw new Exception("未传入签名");
        Map<String, Object> map = new HashMap<>();
        map.put("source", Commonconst.WX_Flag);
        map.put("data", deathApply);
        String data=JSONObject.fromObject(map).toString();
        String jsonStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SIGNER_YZRHDEATH);
        return jsonStr;
    }
}
