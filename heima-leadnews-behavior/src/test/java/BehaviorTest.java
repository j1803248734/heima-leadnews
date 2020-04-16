import com.heima.behavior.BehaviorJarApplication;
import com.heima.behavior.service.AppBehaviorService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.net.nntp.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 蒋蒋
 * Date:  2020/4/16 10:44 下午
 * Package: PACKAGE_NAME
 * Modified By:
 * Description：
 */
@SpringBootTest(classes = BehaviorJarApplication.class)
@RunWith(SpringRunner.class)
public class BehaviorTest {

    @Autowired
    private AppBehaviorService appBehaviorService;
    @Test
    public void testSave(){
        ApUser user = new ApUser();
        user.setId((long) 11);
        AppThreadLocalUtils.setUser(user);
        ShowBehaviorDto showBehaviorDto = new ShowBehaviorDto();
        List<ApArticle> list = new ArrayList<>();
        ApArticle apArticle = new ApArticle();
        apArticle.setId(200);
        list.add(apArticle);
        showBehaviorDto.setArticleIds(list);
        appBehaviorService.saveShowBehavior(showBehaviorDto);
    }

}
