package com.zb.byb.controller;

/**
 * 作者：谢李
 */

import com.github.pagehelper.PageInfo;
import com.zb.byb.common.C;
import com.zb.byb.common.Commonconst;
import com.zb.byb.entity.BillInfo;
import com.zb.byb.entity.TouMiao;
import com.zb.byb.service.BillService;
import com.zb.byb.util.HtmlToImageUtil;
import com.zb.byb.util.Image2Base64Util;
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.List;

/**
 * 对账单
 */
@RestController
@RequestMapping("/api/bill")
public class checkBillController {
    @Autowired
    private BillService billService;

    @ApiOperation("对账单查询列表")
    @GetMapping("/queryBillRecordList")
    @ResponseBody
    public ResponseEntity<?> queryBillRecordList(HttpServletRequest request, BillInfo info)
    {
        try{
            String custId = C.parseStr(request.getSession().getAttribute("custId"));

            List list = billService.queryInfoRecordList(custId, info);
            PageInfo<BillInfo> pageInfo = new PageInfo(list);
            return ResponseEntity.build(200,new Message(), pageInfo);
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }

    @ApiOperation("对账单记录查询")
    @GetMapping("/queryBillRecordById")
    @ResponseBody
    public ResponseEntity<?> queryBillRecordById(HttpServletRequest request, HttpServletResponse response, BillInfo info)
    {
        try{
            String custId = C.parseStr(request.getSession().getAttribute("custId"));
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
            byte[] bytes = HtmlToImageUtil.html2png(Color.white, htmlTemplate, new EmptyBorder(0, 0, 0, 0), HtmlToImageUtil.Width, HtmlToImageUtil.Height);
//            String pngStr = C.newGuid() + ".png";
//            String filePath =Commonconst.TempPath  + pngStr;
//            OutputStream out = new FileOutputStream(new File(filePath));
//            out.write(bytes);
//            out.close();

            System.out.println("data:image/png;base64," + new String(Base64.encodeBase64(bytes)));
            return ResponseEntity.build(200,new Message(), "data:image/png;base64," + new String(Base64.encodeBase64(bytes)));
        }
        catch (Exception e)
        {
            Message message = new Message();
            message.setCode(C.parseStr(Commonconst.FailStatus));
            message.setMessage(e.getMessage());
            return ResponseEntity.build(Commonconst.FailStatus, message);
        }
    }
}
