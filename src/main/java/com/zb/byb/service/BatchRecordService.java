package com.zb.byb.service;

import com.zb.byb.entity.BatchRecord;

public interface BatchRecordService {
    /**
     * 批次记录查看
     * @param batchId
     * @param userId
     * @return
     * @throws Exception
     */
    BatchRecord viewBatchRecord(String batchId, String userId) throws Exception;

    /**
     * 获取批次列表
     * @param
     * @param userId
     * @return
     * @throws Exception
     */
    String getBatchList(String userId, String bizType) throws Exception;

}
