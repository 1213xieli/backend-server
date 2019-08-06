package com.zb.byb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zb.byb.common.Commonconst;
import com.zb.byb.common.Func;
import com.zb.byb.config.EasSession;
import com.zb.byb.entity.*;
import com.zb.byb.service.GoOutApplyService;
import com.zb.byb.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xieli
 * @Date: 2019/7/15 10:28
 * @Description:
 */
@Service
public class GoOutApplyServiceImpl implements GoOutApplyService {

    @Autowired
    private BackTransmitUtil backTransmitUtil;
    @Autowired
    private EasSession easSession;

    @Override
    public String addGoOutApply(GoOutApply record, final HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String sessionId = easSession.getSessionId();
        record.setBizType(MethodName.USERBACKCLEAN_SAVE);
        String custId = Func.parseStr(session.getAttribute("custId"));
        if (Func.checkNullOrEmpty(custId))
            throw new Exception("请重新登录");

        record.setCustId(custId); // 用户id
        record.setCustName(Func.parseStr(session.getAttribute("fname")));// 用户名称

        if (Func.checkNullOrEmpty(record.getBase64RcordId()))
            record.setBizDate(DateUtil.parseDateToStr(new Date(), DateUtil.Time_Formatter_Second));
        if (Func.parseBoolean(record.getSubmit()))
            record.setPlanResultDate(DateUtil.parseDateToStr(new Date(), DateUtil.Time_Formatter_Second));

        // 业务将id进行base64转换
        if (!Func.checkNullOrEmpty(record.getBase64RcordId()))
            record.setRcordId(Image2Base64Util.getBase64Decoder(record.getBase64RcordId()));

        // 进行数据转化。当存在图片时， 从微信服务器上获取图片保存
        addImgs(record);

        List<String> list = new ArrayList();
        list.add(Commonconst.BIOSAFETYAPPHANDLER);
        list.add(sessionId);
        JSONObject map = new JSONObject();
        map.put(Commonconst.DATA, record);
        list.add(map.toString());
        list.add(Commonconst.SWAQ);
        String result = backTransmitUtil.commonInvoke(Commonconst.BUSFACADE, MethodName.METHOD_NAME_HANDLE, list.toArray());
        JSONObject jsonobj = (JSONObject) JSON.parse(result);
        return Func.parseStr(jsonobj.get("msg"));
    }

    /* *
     * @description 存在图片时，将图片从微信服务器上获取
     * @author xieli
     * @date  16:28 2019/7/26
     * @param [goOutApply]
     * @return void
     **/
    private void addImgs(GoOutApply goOutApply) throws Exception {
        if (goOutApply == null || goOutApply.getImgs() == null || goOutApply.getImgs().size() <= 0)
            return;

        // 获取图片列表信息
        List<FileInfo> imgList = goOutApply.getImgs();
        for (FileInfo fileInfo : imgList) {
            if (Func.checkNullOrEmpty(fileInfo.getItemId()) || fileInfo.getData().size() <= 0)
                continue;

            // 根据serverid获取图片
            List<FileEntry> fileEntries = fileInfo.getData();
            for (FileEntry fileEntry : fileEntries) {
                if (Func.checkNullOrEmpty(fileEntry.getServerId()))
                    continue;

                // 图片数据添加
                WeixinUtils.getFileEntryByServerId(fileEntry, fileEntry.getServerId());
            }
        }

    }

    @Override
    public GoOutApply getApplyById(GoOutApply record, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String sessionId = easSession.getSessionId();
        String custId = Func.parseStr(session.getAttribute("custId"));
        if (Func.checkNullOrEmpty(custId))
            throw new Exception("请重新登录");

        record.setCustId(custId); // 用户id
        record.setCustName(Func.parseStr(session.getAttribute("fname")));// 用户名称

        record.setBizType(MethodName.USERBACKCLEAN_VIEW);
        // 业务将id进行base64转换
        if (!Func.checkNullOrEmpty(record.getBase64RcordId()))
            record.setRcordId(Image2Base64Util.getBase64Decoder(record.getBase64RcordId()));

        if (Func.checkNullOrEmpty(record.getRcordId()))
            throw new Exception("未传入消息记录id");

        List<String> list = new ArrayList();
        list.add(Commonconst.BIOSAFETYAPPHANDLER);
        list.add(sessionId);
        JSONObject map = new JSONObject();
        map.put(Commonconst.DATA, record);
        list.add(map.toString());
        list.add(Commonconst.SWAQ);
        String result = backTransmitUtil.commonInvoke(Commonconst.BUSFACADE, MethodName.METHOD_NAME_HANDLE, list.toArray());

        JSONObject jsonObject = JSONObject.parseObject(result);
        if (!"0000".equals(jsonObject.getString("code")))
            throw new Exception("未获取到数据！");

        String dataStr = jsonObject.getString(Commonconst.DATA);
        JSONArray infoArr = JSONArray.parseArray(dataStr);
        JSONObject infoJson = (JSONObject) infoArr.get(0);
        GoOutApply info = JSONObject.parseObject(infoJson.toString(), GoOutApply.class);

        return info;
    }

    @Override
    public List queryClearStep(HttpServletRequest request) throws Exception {
        String sessionId = easSession.getSessionId();

        ClearStepInfo info = new ClearStepInfo();
        info.setBizType(MethodName.QUERYCLEARSTEP);
        info.setType("2"); // 固定值

        List<String> list = new ArrayList();
        list.add(Commonconst.BIOSAFETYAPPHANDLER);
        list.add(sessionId);
        JSONObject map = new JSONObject();
        map.put(Commonconst.DATA, info);
        list.add(map.toString());
        list.add(Commonconst.SWAQ);
        String jsonString = backTransmitUtil.commonInvoke(Commonconst.BUSFACADE, MethodName.METHOD_NAME_HANDLE, list.toArray());

//        net.sf.json.JSONArray jsonArray = JsonPluginsUtil.getListData(jsonString);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (!"0000".equals(jsonObject.getString("code")))
            throw new Exception("未获取到数据！");

        JSONArray jsonArray = jsonObject.getJSONArray(Commonconst.DATA);
        JSONObject jsonObj = (JSONObject) jsonArray.get(0);
        String rcordId = Func.parseStr(jsonObj.get("rcordId"));

        List clearList = JSON.parseArray(jsonObj.getJSONArray("config").toString(), ClearStepInfo.class);

        List result = new ArrayList();
        for (int i = 0; i < clearList.size(); i++) {
            ClearStepInfo step = (ClearStepInfo) clearList.get(i);
            step.setId(rcordId);
            List<Map<String, Object>> itemList = step.getItem();

            List<ItemInfo> itemInfoList = new ArrayList<>();
            for (Map<String, Object> itemMap : itemList) {
                ItemInfo itemInfo = (ItemInfo) Func.map2Object(itemMap, ItemInfo.class);
                itemInfoList.add(itemInfo);
            }
            step.setItemInfoList(itemInfoList);
            step.setItem(null);
            result.add(step);
        }

        return result;
    }


    @Override
    public List getBiologySafeList(GoOutApply record, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String sessionId = easSession.getSessionId();
        String custId = Func.parseStr(session.getAttribute("custId"));
        if (Func.checkNullOrEmpty(custId))
            throw new Exception("请重新登录");

        record.setCustId(custId); // 用户id
        record.setCustName(Func.parseStr(session.getAttribute("fname")));// 用户名称

        record.setBizType(MethodName.USERBACKCLEAN_QUERY);
        record.setApprove("false"); // 固定查询值

        // 通用查询传参list
        List<String> list = new ArrayList();
        list.add(Commonconst.BIOSAFETYAPPHANDLER);
        list.add(sessionId);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(Commonconst.DATA, record);
        list.add(jsonObj.toString());
        list.add(Commonconst.SWAQ);

        String result = backTransmitUtil.commonInvoke(Commonconst.BUSFACADE, MethodName.METHOD_NAME_HANDLE, list.toArray());

        // 转化成对象列表
        List<GoOutApply> goOutApplyList = JsonPluginsUtil.jsonToBeanList(result, GoOutApply.class);

        // 进行一道base64加密传输
        for (GoOutApply info : goOutApplyList) {
            if (Func.checkNullOrEmpty(info.getRcordId()))
                continue;

            info.setBase64RcordId(Image2Base64Util.getBase64Encoder(info.getRcordId()));
        }

        return goOutApplyList;
    }

}
