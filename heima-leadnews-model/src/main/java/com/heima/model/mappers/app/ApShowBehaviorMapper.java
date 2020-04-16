package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApShowBehavior;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 蒋蒋
 * Date:  2020/4/16 9:41 下午
 * Package: com.heima.model.mappers.app
 * Modified By:
 * Description：
 */
public interface ApShowBehaviorMapper {
    /**
     * 查询具体行为
     * @param entryId
     * @param articleIds
     * @return
     */
    List<ApShowBehavior> selectListByEntryIdAndArticleIds(@Param("entryId") Integer entryId, @Param("articleIds") Integer[] articleIds);

    void saveShowBehavior(@Param("articleIds") Integer[] articleIds, @Param("entryId") Integer entryId);

}
