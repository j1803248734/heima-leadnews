package com.heima.behavior.service.impl;

import com.heima.behavior.service.AppBehaviorService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.behavior.pojos.ApBehaviorEntry;
import com.heima.model.behavior.pojos.ApShowBehavior;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mappers.app.ApBehaviorEntryMapper;
import com.heima.model.mappers.app.ApShowBehaviorMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import lombok.val;
import org.apache.commons.net.nntp.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 蒋蒋
 * Date:  2020/4/16 8:30 下午
 * Package: com.heima.behavior.service.impl
 * Modified By:
 * Description：
 */
@Service
@SuppressWarnings("all")
public class AppBehaviorServiceImpl implements AppBehaviorService {

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    @Autowired
    private ApShowBehaviorMapper apShowBehaviorMapper;

    @Override
    public ResponseResult saveShowBehavior(ShowBehaviorDto dto) {
        //获取用户信息
        ApUser user = AppThreadLocalUtils.getUser();
        //根据用户信息或者设备Id查询文章信息
        if(user == null && dto.getEquipmentId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Long userId = null;
        if(user != null){
            userId = user.getId();
        }
        //拿到行为实体
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipmentId(userId,dto.getEquipmentId());
        //获取前台传过来的文章id
        int articleSize = dto.getArticleIds().size();
        Integer[] articleIds = new Integer[articleSize];
        for( int i = 0 ; i < articleSize ; i++ ){
            articleIds[i] =  dto.getArticleIds().get(i).getId();
        }

        if(apBehaviorEntry == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //根据行为id和文章列表id查询app行为表
        List<ApShowBehavior> apShowBehaviors = apShowBehaviorMapper.selectListByEntryIdAndArticleIds(apBehaviorEntry.getId(),articleIds);
        //过滤文章id
        List<Integer> integers = Arrays.asList(articleIds);
        if(apShowBehaviors.isEmpty()){
            apShowBehaviors.forEach(item->{
                Integer articleId = item.getArticleId();
                integers.remove(articleId);
            });
        }
        if(!integers.isEmpty()){
            articleIds = new Integer[integers.size()];
            integers.toArray(articleIds);
            apShowBehaviorMapper.saveShowBehavior(articleIds,apBehaviorEntry.getEntryId());
        }

        return ResponseResult.okResult(0);
    }
}
