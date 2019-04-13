package com.zb.byb.service;

import com.zb.byb.entity.FeedApply;

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
    String queryFeedApply(FeedApply feedApply,String userId) throws Exception;
}
