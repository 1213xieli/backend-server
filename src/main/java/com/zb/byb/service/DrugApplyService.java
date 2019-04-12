package com.zb.byb.service;

import com.zb.byb.entity.DrugApply;

/**
 * 领药申请,业务接口
 * 作者：谢李
 */
public interface DrugApplyService{
    /**
     * 通过登入人id获取投苗
     * @param userId
     * @return
     * @throws Exception
     */
    String queryListByUser(String userId) throws Exception;

    /**
     * 投苗数据保存
     * @param info
     * @return
     * @throws Exception
     */
    boolean saveInfo(DrugApply info) throws Exception;

    /**
     * 根据前台传过来的tokenid，初始化数据
     * @param tokenId
     * @return 返回对象
     * @throws Exception 异常
     */
    DrugApply queryListInitData(String tokenId) throws Exception;

    /**
     * 根据用户id查询到投苗记录
     * @param tokenId
     * @return 返回对象
     * @throws Exception 异常
     */
    DrugApply queryInfoRecordList(String tokenId) throws Exception;
}
