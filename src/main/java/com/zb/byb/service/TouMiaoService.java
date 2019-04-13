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

    /**
     * 根据前台传过来的tokenid，初始化数据
     * @param tokenId
     * @return 返回对象
     * @throws Exception 异常
     */
    TouMiao queryListInitData(String tokenId) throws Exception;

    /**
     * 根据用户id查询到投苗记录
     * @param tokenId
     * @return 返回对象
     * @throws Exception 异常
     */
    TouMiao queryInfoRecordList(String tokenId) throws Exception;

}
