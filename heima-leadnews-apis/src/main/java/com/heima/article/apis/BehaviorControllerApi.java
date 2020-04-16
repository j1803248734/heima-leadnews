package com.heima.article.apis;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * Package: com.heima.article.apis
 * Description：
 * @Author: 蒋蒋
 * Date:  2020/4/16 8:20 下午
 * Modified By:
 *
 */
public interface BehaviorControllerApi {
    /**
     * 保存用户点击文章的行为
     * @param dto
     * @return
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
