package com.zb.byb.service;

import com.zb.byb.entity.FeedApply;
import com.zb.byb.entity.FeedRecord;

public interface FeedRecordService {
    /**
     * 饲喂添加
     * @param feedRecord 饲喂记录
     * @param userId 养户id
     * @return
     */
    String addFeedRecord(FeedRecord feedRecord, String userId)throws Exception;

    /**
     *饲喂记录
     * @param userId 养户id
     * @return
     */
    String queryFeedRecord(String batchId,String userId)throws Exception;

    /**
     * 序时簿查看
     * @param recordId
     * @param userId
     * @return
     */
    String queryFeedRecordList(String recordId,String userId);
}
