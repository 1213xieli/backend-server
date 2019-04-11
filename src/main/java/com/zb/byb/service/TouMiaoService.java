package com.zb.byb.service;

import com.zb.byb.entity.TouMiao;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 投苗业务层处理
 * 作者：谢李
 */
public interface TouMiaoService
{
    /**
     * 通过登入人id获取投苗
     * @param userId
     * @return
     * @throws Exception
     */
    String queryListByUser(String userId) throws Exception;

    /**
     * 投苗数据保存
     * @param info
     * @return
     * @throws Exception
     */
    boolean saveInfo(TouMiao info) throws Exception;

}
