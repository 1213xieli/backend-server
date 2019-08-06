package com.zb.byb.service;

import com.zb.byb.entity.GoOutApply;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/* *
 * @description 申请外出接口
 * @author xieli
 * @date  9:55 2019/7/30
 * @param
 * @return
 **/
public interface GoOutApplyService {

    //A-生物安全-养户消毒保存
    public String addGoOutApply(GoOutApply record, final HttpServletRequest request) throws Exception;

    // 获取消毒单据根据id
    public GoOutApply getApplyById(GoOutApply record, final HttpServletRequest request) throws Exception;

    //获取生物安全信息列表
    public List getBiologySafeList(GoOutApply record, final HttpServletRequest request) throws Exception;

    // A-生物安全-洗消步骤
    public List queryClearStep(HttpServletRequest request) throws Exception;
}
