package com.zb.byb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.byb.common.CommonFunc;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.DataRecord;
import com.zb.byb.entity.Farmer;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.MyInfoService;
import com.zb.byb.service.TouMiaoService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.DateUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 投苗
 * 作者：谢李
 */
@Service
public class TouMiaoServiceImpl implements TouMiaoService {
    @Autowired
    private MyInfoService myInfoService;

    @Override
    public String queryListByUser(String openId) throws Exception {
        return null;
    }

    @Override
    public String saveInfo(TouMiao info) throws Exception {
        if (info == null)
        {
            throw new Exception("无法保存");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("openId", Commonconst.OpenId);
        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);

        String data = JSONObject.fromObject(map).toString();
        String jsonBackStr = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_SAVE_PIGINGAPPLY);
        System.out.println(jsonBackStr);

        return JsonPluginsUtil.isRequestSuccessBackId(jsonBackStr);
    }

    @Override
    public TouMiao queryListInitData(String custId) throws Exception {

        Map<String, Object> map = new HashMap<>();
//        map.put("openId", Commonconst.OpenId);
//        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        TouMiao queryInfo = new TouMiao();
        queryInfo.setCustId(custId);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_CUSTINFO);

        JSONObject jsonObject = JSONObject.fromObject(jsonData);
        String obj = jsonObject.getString(JsonPluginsUtil.Data);
        JSONObject custInfoStr = JSONObject.fromObject(obj);
        TouMiao info = new TouMiao();
        info.setCustName(custInfoStr.getString("fname"));
        info.setScope(custInfoStr.getString("cfwinternum"));

//        TouMiao info = JsonPluginsUtil.jsonToBean(jsonData, TouMiao.class);
//        if (info == null)
//            info = new TouMiao();

        return info;
    }

    /**
     *  金碟软件传回的数据,, 此处进行一道 公共的转化。转化成对象传回到头台
     * 				temp.put("rcordId",rs.getString("fid"));
     * 				temp.put("applyDate", rs.getString("fbizdate"));
     * 				temp.put("remark",rs.getString("fremark"));
     * 				temp.put("custName",rs.getString("custName"));
     * 				temp.put("custId",rs.getString("custId"));
     * 				temp.put("villageName",rs.getString("villName"));
     * 				temp.put("villId",rs.getString("villId"));
     * 				temp.put("oneHandRent",rs.getString("fonehandrent"));
     * 				temp.put("identityCards",rs.getString("fcardnumber"));
     * 				temp.put("num",rs.getBigDecimal("fqty"));
     * 				temp.put("billStatusIndex",rs.getString("fbillstatus"));
     * 				temp.put("billStatus",BillStatesEnum.getEnum(rs.getString("fbillstatus")).getAlias());
     * 				temp.put("balance",rs.getBigDecimal("fbalance"));
     * @param jsonStr
     * @return
     */
    private TouMiao convertInfoByJson(String jsonStr)
    {
        if (CommonFunc.checkNullOrEmpty(jsonStr))
            return new TouMiao();

        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        String obj = jsonObject.getString(JsonPluginsUtil.Data);
        if (CommonFunc.checkNullOrEmpty(obj))
            return new TouMiao();

        JSONObject backMap = JSONObject.fromObject(obj);
        TouMiao result = new TouMiao();
        result.setRcordId(backMap.getString("rcordId"));
        if (null != backMap.getString("applyDate"))
            result.setApplyDate(DateUtil.parseDateOnly(backMap.getString("applyDate")));
        result.setRemark(backMap.getString("remark"));
        result.setCustName(backMap.getString("custName"));
        result.setCustId(backMap.getString("custId"));
        result.setVillageName(backMap.getString("villageName"));
        result.setVillId(backMap.getString("villId"));
        result.setOneHandRent(CommonFunc.parseDbl(backMap.getString("oneHandRent")));
        result.setIdentityCards(backMap.getString("identityCards"));
        result.setNum(CommonFunc.parseInt(backMap.getString("num")));
        result.setBillStatusIndex(CommonFunc.parseInt(backMap.getString("billStatusIndex")));
        result.setBillStatus(backMap.getString("billStatus"));
        result.setBalance(backMap.getString("balance"));

        return result;
    }

    @Override
    public TouMiao queryInfoById(String tmid) throws Exception {
        if (CommonFunc.checkNullOrEmpty(tmid))
            return new TouMiao();

        Map<String, Object> map = new HashMap<>();
//        map.put("openId", Commonconst.OpenId);
//        map.put("custId", Commonconst.CustId);
        // 微信入口获取数据，统一标识
        map.put("source", Commonconst.WX_Flag);
        TouMiao  queryInfo = new TouMiao();
        queryInfo.setRcordId(tmid);
        map.put("data", queryInfo);

        // 要传入数据进行转化
        String data = JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_VIEW_PIGINGAPPLY);
        return JsonPluginsUtil.jsonToBean(jsonData, TouMiao.class);
    }

    @Override
    public List<TouMiao> queryInfoRecordList(String tokenId) throws Exception
    {
        if (CommonFunc.checkNullOrEmpty(tokenId))
            return new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        TouMiao queryInfo = new TouMiao();
//        queryInfo.setRcordId(tokenId);
        queryInfo.setCustId(tokenId);
//        map.put("openId", Commonconst.OpenId);
//        map.put("custId", Commonconst.CustId);
        map.put("source", Commonconst.WX_Flag);
        map.put("data", queryInfo);
//        map.put("pageNumber","1");
//        map.put("pageSize","1000");

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_PIGINGAPPLY);
        return JsonPluginsUtil.jsonToBeanList(jsonData, TouMiao.class);
    }
}
