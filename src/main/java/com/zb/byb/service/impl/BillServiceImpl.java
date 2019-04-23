package com.zb.byb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.BillInfo;
import com.zb.byb.entity.EquipmentApply;
import com.zb.byb.service.BillService;
import com.zb.byb.util.BackTransmitUtil;
import com.zb.byb.util.HtmlToImageUtil;
import com.zb.byb.util.JsonPluginsUtil;
import com.zb.byb.util.MethodName;
import org.springframework.stereotype.Service;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：谢李
 */
@Service
public class BillServiceImpl implements BillService {


    @Override
    public List queryInfoRecordList(BillInfo info) throws Exception {
//        BillInfo test = new BillInfo();
//        test.setId("xieli");
//        test.setRecordId("recordId");
//        test.setCustName("谢李");
//        test.setBillDate("2019-04-19");

        if (C.checkNullOrEmpty(info.getCustId()))
            throw new Exception("未传入养户id");

        Map<String, Object> map = new HashMap<>();
        map.put("custId", info.getCustId());
        map.put("source", Commonconst.WX_Flag);
        map.put("data", info);

        // 要传入数据进行转化
        String data = JSONObject.toJSONString(map);
        String jsonData = BackTransmitUtil.invokeFunc(data, MethodName.Method_Name_checkBill);
        System.out.println("对账单，查询query方法----" + jsonData);

        return JsonPluginsUtil.jsonToBeanList(jsonData, BillInfo.class);
    }

    @Override
    public String queryBillRecordById( BillInfo info) throws Exception {
        String htmlTemplate = "    <table border=\"1\" width=\"60%\" bgcolor=\"#e9faff\" cellpadding=\"2\">\r\n" +
                "        <caption>课程表</caption>\r\n" +
                "        <tr align=\"center\">\r\n" +
                "            <td colspan=\"2\">时间\\日期</td>\r\n" +
                "            <td>一</td>\r\n" +
                "            <td>二</td>\r\n" +
                "            <td>三</td>\r\n" +
                "            <td>四</td>\r\n" +
                "            <td>五</td>\r\n" +
                "        </tr>\r\n" +
                "\r\n" +
                "        <tr align=\"center\">\r\n" +
                "            <td rowspan=\"2\">上午</td>\r\n" +
                "            <td>9:30-10:15</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "        </tr>\r\n" +
                "\r\n" +
                "        <tr align=\"center\">\r\n" +
                "            <td>10:25-11:10</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "        </tr>\r\n" +
                "\r\n" +
                "        <tr>\r\n" +
                "            <td colspan=\"7\">&nbsp;</td>\r\n" +
                "        </tr>\r\n" +
                "\r\n" +
                "        <tr align=\"center\">\r\n" +
                "            <td rowspan=\"2\">下午</td>\r\n" +
                "            <td>14:30-15:15</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "        </tr>\r\n" +
                "\r\n" +
                "        <tr align=\"center\">\r\n" +
                "            <td>15:25-16:10</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "            <td>语文</td>\r\n" +
                "        </tr>\r\n" +
                "    </table>";
        System.out.println(htmlTemplate);
//        byte[] bytes = HtmlToImageUtil.html2png(Color.white, htmlTemplate, new EmptyBorder(0, 0, 0, 0), HtmlToImageUtil.Width, HtmlToImageUtil.Height);
//        String filePath =Commonconst.TempPath  + C.newGuid() + ".png";
//        OutputStream out = new FileOutputStream(new File(filePath));
//        out.write(bytes);
//        out.close();

        return htmlTemplate;
    }
}
