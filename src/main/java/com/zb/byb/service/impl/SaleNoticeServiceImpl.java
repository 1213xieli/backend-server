package com.zb.byb.service.impl;

import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.SaleNotice;
import com.zb.byb.service.SaleNoticeService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import com.zb.byb.common.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author: shaoys
 * @Mail: 415992946@qq.com
 * @Date: Created in 10:02 2019/5/5
 **/
@Service
public class SaleNoticeServiceImpl implements SaleNoticeService {

    @Override
    public List<SaleNotice> getSaleRecordList(String id) throws Exception {
        if (C.checkNullOrEmpty(id))
            return new ArrayList<>();

        SaleNotice saleNotice = new SaleNotice();
        saleNotice.setCustId(id);
        Map<String, Object> map = new HashMap<>();
//        map.put("openId", Commonconst.OpenId);
        map.put("custId", id);
        map.put("data", saleNotice);
        map.put("pageNumber","1");
        map.put("pageSize","1000");

        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_VIEW_SALE);
        System.out.println("销售列表，查询列表---" + jsonData + "1111111111111111111");
        return JsonPluginsUtil.jsonToBeanList(jsonData, SaleNotice.class);
    }

    @Override
    public SaleNotice getQuerySale(String id) throws Exception {
        if (C.checkNullOrEmpty(id))
            return new SaleNotice();

        SaleNotice saleNotice = new SaleNotice();
        saleNotice.setRcordId(id);
        Map<String, Object> map = new HashMap<>();
        map.put("data", saleNotice);
        // 要传入数据进行转化
        String data= JSONObject.fromObject(map).toString();
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.METHOD_NAME_QUERY_QUERY_SALE);
        System.out.println("单个销售,查询列表---" + jsonData);
        return JsonPluginsUtil.jsonToBean(jsonData, SaleNotice.class);
    }
}
