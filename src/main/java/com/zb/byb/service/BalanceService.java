package com.zb.byb.service;

import com.zb.byb.entity.Balance;
import com.zb.byb.entity.BalanceRecord;

public interface BalanceService {
    /**
     * 结算申请
     * @param balance 结算批次号
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    String balanceApply(Balance balance, String userId) throws Exception;

    /**
     * 结算记录
     * @return
     * @throws Exception
     */
    String getBalanceRecord(BalanceRecord balanceRecord, String userId)throws Exception;


}
