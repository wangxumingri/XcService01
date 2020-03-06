package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    private final PageService pageService;

    public CmsPageController(PageService pageService) {
        this.pageService = pageService;
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    @SuppressWarnings("unchecked")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        QueryResult queryResult = pageService.findList(page, size, queryPageRequest);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult addPage(@RequestBody CmsPage cmsPage) {
       return pageService.addPage(cmsPage);
    }
}
