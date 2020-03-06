package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.response.CmsSiteSelectQueryResult;
import com.xuecheng.framework.domain.cms.response.dto.SiteSelectDto;
import com.xuecheng.manage_cms.dao.CmsSiteRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteService {

    private final CmsSiteRepository cmsSiteRepository;

    @Autowired
    public SiteService(CmsSiteRepository cmsSiteRepository) {
        this.cmsSiteRepository = cmsSiteRepository;
    }

    public List<SiteSelectDto>  findSiteSelect(){
        List<CmsSite> cmsSites = cmsSiteRepository.findAll();
        List<SiteSelectDto> siteSelectDtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(cmsSites)) {
            for (CmsSite cmsSite : cmsSites) {
                SiteSelectDto siteSelectDto = new SiteSelectDto();
                siteSelectDto.setSiteId(cmsSite.getSiteId());
                siteSelectDto.setSiteName(cmsSite.getSiteName());
                siteSelectDtos.add(siteSelectDto);
            }
        }
        return siteSelectDtos;
    }
}
