package com.zb.byb.service;

public interface BalanceService {
    /**
     * 结算申请
     * @param batchId 结算批次号
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    String balanceApply(String batchId,String userId) throws Exception;

    String getBalanceRecord()throws Exception;
}
