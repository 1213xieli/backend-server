package com.zb.byb.service;

public interface BatchRecordService {
    /**
     * 批次记录查看
     * @param batchId
     * @param userId
     * @return
     * @throws Exception
     */
    String viewBatchRecord(String batchId,String userId) throws Exception;

    /**
     * 获取批次列表
     * @param batchId
     * @param userId
     * @return
     * @throws Exception
     */
    String getBatchList(String userId, String bizType) throws Exception;

}
