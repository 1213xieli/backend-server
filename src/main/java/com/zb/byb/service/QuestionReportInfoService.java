package com.zb.byb.service;

import com.zb.byb.entity.NormalQuestionInfo;
import com.zb.byb.entity.QuestionReportInfo;

import java.util.List;
import java.util.Map;

/**
 * 作者：谢李
 */
public interface QuestionReportInfoService
{
    /**
     * 保存反馈信息
     * @param info 问题反馈
     * @throws Exception 异常
     */
    void saveQuestionReport(QuestionReportInfo info)throws Exception;

    /**
     * 根据参数,进行常见问题获取
     * @param
     * @return
     * @throws Exception
     */
    List<NormalQuestionInfo> queryNormalQuestionList(Map<String, Object> paramMap) throws Exception;
}
