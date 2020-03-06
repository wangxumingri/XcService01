package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * cms请求API
 */
public interface CmsPageControllerApi {
    /**
     * 分页查询列表
     * @param page 当前页
     * @param size 每页显示记录数
     * @param queryPageRequest 其他请求参数
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);



    /**
     * 新增cms页面
     * @param cmsPage 页面信息
     * @return 成功：返回，新增页面信息，失败：返回提示信息
     */
    CmsPageResult addPage(CmsPage cmsPage);


}
