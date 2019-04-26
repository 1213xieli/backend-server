package com.zb.byb.service;

import org.springframework.stereotype.Service;


public interface SaleNoticeService {
    /**
     * 销售通知信息
     * @param custId
     * @return
     * @throws Exception
     */
    String getSaleNotice(String custId)throws Exception;
}
