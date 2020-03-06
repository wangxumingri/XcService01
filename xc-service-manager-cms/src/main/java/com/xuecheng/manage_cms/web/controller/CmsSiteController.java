package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsSIteControllerApi;
import com.xuecheng.framework.domain.cms.response.CmsSiteSelectQueryResult;
import com.xuecheng.framework.domain.cms.response.dto.SiteSelectDto;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.manage_cms.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/site")
public class CmsSiteController implements CmsSIteControllerApi {

    private final SiteService siteService;

    @Autowired
    public CmsSiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    @GetMapping("/select")
    public CmsSiteSelectQueryResult findSiteSelect() {
        List<SiteSelectDto> siteSelect = siteService.findSiteSelect();
        return new CmsSiteSelectQueryResult(CommonCode.SUCCESS,siteSelect);
    }
}
