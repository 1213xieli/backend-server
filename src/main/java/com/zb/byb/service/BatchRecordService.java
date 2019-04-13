package com.zb.byb.service;

public interface BatchRecordService {
    /**
     * 记录查看
     * @param batchId
     * @param userId
     * @return
     * @throws Exception
     */
    String viewBatchRecord(String batchId,String userId) throws Exception;
}
