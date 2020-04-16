package com.heima.behavior.controller.v1;

import com.heima.article.apis.BehaviorControllerApi;
import com.heima.behavior.service.AppBehaviorService;
import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package: com.heima.behavior.controller.v1
 * Description：
 *
 * @Author: 蒋蒋
 * Date:  2020/4/16 8:24 下午
 * Modified By:
 */
@RestController
@RequestMapping("/api/v1/behavior")
@SuppressWarnings("all")
public class  BehaviorController implements BehaviorControllerApi {

    @Autowired
    private AppBehaviorService appBehaviorService;

    @Override
    @RequestMapping("save_behavior ")
    public ResponseResult saveShowBehavior(ShowBehaviorDto dto) {
        return appBehaviorService.saveShowBehavior(dto);
    }
}
