package com.heima.model.mappers.app;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApUserArticleListMapper {
    /**
     * 查询用户的推荐文章信息
     * @param user
     * @param dto
     * @param type
     * @return
     */
    List<ApUserArticleList> getLoadArticleIdListById(@Param("user") ApUser user,@Param("dto") ArticleHomeDto dto,@Param("type") Short type);
}
