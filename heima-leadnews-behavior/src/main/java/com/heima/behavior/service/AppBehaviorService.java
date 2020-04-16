package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Author: 蒋蒋
 * Date:  2020/4/16 8:28 下午
 * Package: com.heima.behavior.service
 * Modified By:
 * Description：
 */
public interface AppBehaviorService {
    /**
     * 保存用户行为
     * @param dto
     * @return
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
