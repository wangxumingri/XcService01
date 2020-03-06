package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.domain.cms.response.dto.SiteSelectDto;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;

import java.util.List;

@Data
public class CmsSiteSelectQueryResult extends ResponseResult {
    private List<SiteSelectDto> siteList;

    public CmsSiteSelectQueryResult(ResultCode resultCode, List<SiteSelectDto> siteSelectDto) {
        super(resultCode);
        this.siteList = siteSelectDto;
    }
}


