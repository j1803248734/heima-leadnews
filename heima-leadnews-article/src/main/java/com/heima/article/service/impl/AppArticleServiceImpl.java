package com.heima.article.service.impl;

import com.heima.article.service.AppArticleService;
import com.heima.common.common.contants.ArticleContants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("all")
public class AppArticleServiceImpl implements AppArticleService {

    @Autowired
    private ApArticleMapper apArticleMapper;
    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //程序的严谨性 参数校验
         if(dto == null){
             dto = new ArticleHomeDto();
         }
         //时间校验
        if(dto.getMinBehotTime() == null){
            dto.setMinBehotTime(new Date());
        }
        if(dto.getMaxBehotTime() == null){
            dto.setMaxBehotTime(new Date());
        }
        //分页参数校验
        Integer size = dto.getSize();
        if(size == null || size <= 0){
            size = ArticleContants.LOAD_SIZE_INITIAL_VALUE;
        }
        size = Math.min(ArticleContants.LOAD_SIZE_MAX_VALUE,size);
        dto.setSize(size);
        //文章信息校验
        String tag = dto.getTag();
        if(StringUtils.isBlank(tag)) {
            tag = ArticleContants.DEFAULT_TAG;
            dto.setTag(tag);
        }

        //类型参数校验
        if(!type.equals(ArticleContants.LOAD_TYPE_LOAD_MORE)&&!type.equals(ArticleContants.LOAD_TYPE_LOAD_NEW)) {
            type = ArticleContants.LOAD_TYPE_LOAD_MORE;
        }
        //拿到用户信息
        ApUser user = AppThreadLocalUtils.getUser();
        //判断当前用户
        if(user != null){
            //当前有用户 推送推荐文章
            List<ApArticle> apUserlist = this.getUserArticle(user,dto,type);
            return ResponseResult.okResult(apUserlist);
        }else{
            //当前无用户 推送默认文章
            List<ApArticle> defaultlist = this.getDefaultArticle(dto,type);
            return ResponseResult.okResult(defaultlist);
        }


    }

    /**
     * 直接拿到默认文章信息
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto,type);
    }

    /**
     * 从用户推荐表中查找文章信息 如果没有则从默认文章信息获取数据
     * @param apUser
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list = apUserArticleListMapper.getLoadArticleIdListById(user,dto,type);
        if(!list.isEmpty()){
            return apArticleMapper.loadArticleListByIdList(list);
        }else{
            return getDefaultArticle(dto,type);
        }
    }
}
