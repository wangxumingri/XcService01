package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.domain.cms.response.dto.SiteSelectDto;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResultCode;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    @Autowired
    private CmsPageRepository cmsPageRepository;

    @SuppressWarnings("unchecked")
    public QueryResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        if (page < 1) {
            page = 1;
        }
        page--; // 索引从0开始
        if (size < 1) {
            size = 9;
        }
        // 分页对象
        Pageable pageable = PageRequest.of(page, size);
        QueryResult queryResult = new QueryResult();

        // 条件查询
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }
        CmsPage cmsPage = new CmsPage();
        // 根据站点ID精确查询
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        // 根据页面ID精确查询
        if (StringUtils.isNotEmpty(queryPageRequest.getPageId())) {
            cmsPage.setPageId(queryPageRequest.getPageId());
        }
        // 根据模板ID精确查询
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        // 根据页面名称精确查询
        if (StringUtils.isNotEmpty(queryPageRequest.getPageName())) {
            cmsPage.setPageName(queryPageRequest.getPageName());
        }
        // 根据页面别名模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> cmsPageExample = Example.of(cmsPage,exampleMatcher);
        // 查询结果
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(cmsPageExample,pageable);

        queryResult.setList(cmsPages.getContent());
        queryResult.setTotal(cmsPages.getTotalElements());

        return queryResult;
    }

    public CmsPageResult addPage(CmsPage cmsPage){
        CmsPageResult cmsPageResult = null;
        if (cmsPage == null){
            ExceptionCast.cast(CommonCode.INVALIDPARAM);
        }
        CmsPage existingCmsPage = cmsPageRepository.findByPageNameAndPageWebPathAndSiteId(cmsPage.getPageName(), cmsPage.getPageWebPath(), cmsPage.getSiteId());
        if (existingCmsPage == null){
            // 使用自动生成的主键
            cmsPage.setPageId(null);
            CmsPage newCmsPage = cmsPageRepository.save(cmsPage);
            cmsPageResult = new CmsPageResult(CommonCode.SUCCESS,newCmsPage);
        }else {
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }

        return cmsPageResult;
    }
}
