package com.zb.byb.service;

import com.zb.byb.entity.EquipmentApply;

/**
 * 设备领用申请,业务接口
 * 作者：谢李
 */
public interface EquipmentApplyService {
    /**
     * 通过登入人id获取设备领用申请
     * @param userId
     * @return
     * @throws Exception
     */
    String queryListByUser(String userId) throws Exception;

    /**
     * 设备领用申请数据保存
     * @param info
     * @return
     * @throws Exception
     */
    boolean saveInfo(EquipmentApply info) throws Exception;

    /**
     * 根据前台传过来的tokenid，初始化数据
     * @param tokenId
     * @return 返回对象
     * @throws Exception 异常
     */
    EquipmentApply queryListInitData(String tokenId) throws Exception;

    /**
     * 根据用户id查询到设备领用申请记录
     * @param tokenId
     * @return 返回对象
     * @throws Exception 异常
     */
    EquipmentApply queryInfoRecordList(String tokenId) throws Exception;
}
