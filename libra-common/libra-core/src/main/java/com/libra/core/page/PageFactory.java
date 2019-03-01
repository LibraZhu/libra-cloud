package com.libra.core.page;

import com.baomidou.mybatisplus.plugins.Page;
import com.libra.core.context.RequestDataHolder;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.util.EmptyUtil;
import com.libra.core.util.HttpContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 默认分页参数构建
 */
public class PageFactory {
    /**
     * 排序，升序还是降序
     */
    private static final String ASC = "asc";

    /**
     * 每页大小的param名称
     */
    private static final String PAGE_SIZE_PARAM_NAME = "pageSize";

    /**
     * 第几页的param名称
     */
    private static final String PAGE_NO_PARAM_NAME = "pageNo";

    /**
     * 升序还是降序的param名称
     */
    private static final String SORT_PARAM_NAME = "sort";

    /**
     * 根据那个字段排序的param名称
     */
    private static final String ORDER_BY_PARAM_NAME = "orderBy";

    /**
     * 默认规则的分页
     */
    public static <T> Page<T> defaultPage() {

        int pageSize = 20;
        int pageNo = 1;

        HttpServletRequest request = HttpContext.getRequest();

        if (request == null) {
            return new Page<>(pageNo, pageSize);
        } else {
            //每页条数
            String pageSizeString = getFieldValue(request, PAGE_SIZE_PARAM_NAME);
            if (EmptyUtil.isNotEmpty(pageSizeString)) {
                pageSize = Integer.valueOf(pageSizeString);
            }

            //第几页
            String pageNoString = getFieldValue(request, PAGE_NO_PARAM_NAME);
            if (EmptyUtil.isNotEmpty(pageNoString)) {
                pageNo = Integer.valueOf(pageNoString);
            }

            //获取排序字段和排序类型(asc/desc)
            String sort = getFieldValue(request, SORT_PARAM_NAME);
            String orderByField = getFieldValue(request, ORDER_BY_PARAM_NAME);

            if (EmptyUtil.isEmpty(sort)) {
                Page<T> page = new Page<>(pageNo, pageSize);
                page.setOpenSort(false);
                return page;
            } else {
                Page<T> page = new Page<>(pageNo, pageSize, orderByField);
                if (ASC.equalsIgnoreCase(sort)) {
                    page.setAsc(true);
                } else {
                    page.setAsc(false);
                }
                return page;
            }
        }

    }

    /**
     * 自定义参数的分页
     */
    public static <T> Page<T> createPage(PageQuery pageQuery) {

        int pageSize = 20;
        int pageNo = 1;

        if (pageQuery == null) {
            Page<T> page = new Page<>(pageNo, pageSize);
            page.setOpenSort(false);
            return page;
        } else {
            if (EmptyUtil.isNotEmpty(pageQuery.getPageSize())) {
                pageSize = pageQuery.getPageSize();
            }

            if (EmptyUtil.isNotEmpty(pageQuery.getPageNo())) {
                pageNo = pageQuery.getPageNo();
            }

            if (EmptyUtil.isEmpty(pageQuery.getSort())) {
                Page<T> page = new Page<>(pageNo, pageSize);
                page.setOpenSort(false);
                return page;
            } else {
                Page<T> page = new Page<>(pageNo, pageSize, pageQuery.getOrderByField());
                if (ASC.equalsIgnoreCase(pageQuery.getSort())) {
                    page.setAsc(true);
                } else {
                    page.setAsc(false);
                }
                return page;
            }
        }
    }

    /**
     * 获取参数值，通过param或从requestBody中取
     */
    private static String getFieldValue(HttpServletRequest request, String fieldName) {
        String parameter = request.getParameter(fieldName);
        if (parameter == null) {
            RequestData requestData = RequestDataHolder.get();
            if (requestData == null) {
                return null;
            } else {
                Object fieldValue = requestData.get(fieldName);
                if (fieldValue == null) {
                    return null;
                } else {
                    return fieldValue.toString();
                }
            }
        } else {
            return null;
        }
    }
}
