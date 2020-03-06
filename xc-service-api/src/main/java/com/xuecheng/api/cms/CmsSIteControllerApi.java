package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.response.CmsSiteSelectQueryResult;

public interface CmsSIteControllerApi {

    /**
     * 获取站点下拉框
     * @return
     */
    CmsSiteSelectQueryResult findSiteSelect();
}
