package com.zb.byb.service;

import com.zb.byb.entity.FeedApply;

import java.util.List;

public interface FeedApplyService {
    /**
     *
     * @param feedApply 领料申请
     * @param userId  用户id
     * @return
     * @throws Exception
     */
    String feedApply(FeedApply feedApply,String userId) throws Exception;

    /**
     *
     * @param feedApply 领料申请
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    List<FeedApply> queryFeedApply(FeedApply feedApply, String userId) throws Exception;

    /**
     * 查看领料详情
     * @param rcordId
     * @return
     * @throws Exception
     */
    FeedApply viewFeedApply(String rcordId)throws Exception;

    String singer(FeedApply feedApply) throws Exception;
}
