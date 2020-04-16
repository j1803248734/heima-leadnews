package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApBehaviorEntry;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 蒋蒋
 * Date:  2020/4/16 9:07 下午
 * Package: com.heima.model.mappers.app
 * Modified By:
 * Description：
 */
public interface ApBehaviorEntryMapper {
    /**
     * 获取文章Id
     * @param userId
     * @param equipmentId
     * @return
     */
    ApBehaviorEntry selectByUserIdOrEquipmentId(@Param("userId") Long userId, @Param("equipmentId") Integer equipmentId);
}
