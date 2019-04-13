package com.zb.byb.service;

import com.zb.byb.entity.DeathApply;

public interface DeathApplyService {
    /**
     *
     * @param deathApply 死亡申报单
     * @param userId 用户id
     * @return
     */
    String deathApply(DeathApply deathApply,String userId) throws Exception;

    /**
     * 死亡申报记录
     * @param userId
     * @return
     */
    String getDeathApplyRecord(String userId)throws  Exception;



}
