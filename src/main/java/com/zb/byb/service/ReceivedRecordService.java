package com.zb.byb.service;

import java.util.List;

public interface ReceivedRecordService {
    /**
     * 领用记录查询
     * @param custId 养户id
     * @param obj 查询条件对象
     * @return
     * @throws Exception
     */
    List getReceivedList(String custId,Object obj)throws Exception;
}
