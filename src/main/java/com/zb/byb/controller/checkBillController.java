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
import com.zb.framework.common.entity.Message;
import com.zb.framework.common.entity.ResponseEntity;
import io.swagger.annotations.ApiOperation;
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
            String filePath =Commonconst.TempPath  + C.newGuid() + ".png";
//            OutputStream out = new FileOutputStream(new File(filePath));
//            out.write(bytes);
//            out.close();

            String base64str=new String(bytes);//log为实体 括号里面是图像的get方法 返回为Byte[]型
            String newstr=new String("\"data:image/jpg;base64,"+base64str+"\"");//拼装Base64字符串头
            return ResponseEntity.build(200,new Message(), newstr);
//            response.getWriter().write(newstr);//将完整Base64字符串返回前台

//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            os.write(bytes);
//            byte[] content = os.toByteArray();
//            InputStream is = new ByteArrayInputStream(content);
//// 设置response参数，可以打开下载页面
//            response.reset();
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            ServletOutputStream out = null;
//            BufferedInputStream bis = null;
//            BufferedOutputStream bos = null;
//            try
//            {
//                response.setHeader("Content-Disposition",
//                        "attachment;filename="
//                                + new String((C.newGuid() + ".png").getBytes("UTF-8"),
//                                "iso-8859-1"));
//                out = response.getOutputStream();
//                bis = new BufferedInputStream(is);
//                bos = new BufferedOutputStream(out);
//                byte[] buff = new byte[2048];
//                int bytesRead;
//                // Simple read/write loop.
//                while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
//                {
//                    bos.write(buff, 0, bytesRead);
//                }
//            } catch (final IOException e)
//            {
//
//            } finally
//            {
//                try
//                {
//                    if (bis != null)
//                    {
//                        bis.close();
//                    }
//                    if (bos != null)
//                    {
//                        bos.close();
//                    }
//                } catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//            return ResponseEntity.build(200,new Message(), billService.queryBillRecordById(custId, info));
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
