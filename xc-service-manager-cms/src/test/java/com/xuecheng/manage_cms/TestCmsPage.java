package com.xuecheng.manage_cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCmsPage {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Test
    public void testSave() {
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageId("123456789");
        cmsPage.setSiteId("987465123");
        cmsPage.setPageName("测试页面");

        cmsPageRepository.save(cmsPage);
    }

    @Test
    public void testFind() {
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageId("123456789");
        cmsPage.setSiteId("987465123");
        cmsPage.setPageName("测试页面");

        Optional<CmsPage> pageOptional = cmsPageRepository.findById("123456789");

        CmsPage cmsPage1 = pageOptional.get();
    }

    @Test
    public void testDelete() {
        cmsPageRepository.deleteById("123456789");
    }


    @Test
    public void testUpdate() {
        Optional<CmsPage> pageOptional = cmsPageRepository.findById("123456789");
        if (pageOptional.isPresent()) {
            CmsPage cmsPage = pageOptional.get();
            cmsPage.setPageName("修改");
            cmsPageRepository.save(cmsPage);
        }
    }

    /**
     * 设置值时精确匹配：
     *  属性都不赋值，查询全部，
     *  设置值，自动根据设置的值进行查询
     *
     * 模糊匹配：
     *  模糊匹配使用ExampleMatcher
     *
     */
    @Test
    public void testFindByExample() {
        Pageable p = PageRequest.of(0, 10);
        CmsPage cmsPage = new CmsPage();
//        cmsPage.setPageName("10101.html");
        cmsPage.setPageAliase("课程详情页面");
        Example<CmsPage> example = Example.of(cmsPage, ExampleMatcher.matching());
        Page<CmsPage> all = cmsPageRepository.findAll(example, p);
        List<CmsPage> content = all.getContent();
    }


}
