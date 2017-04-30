package com.tangcheng.app.service.util;

import java.util.Collections;
import java.util.List;

/**
 * Created by tangcheng on 4/30/2017.
 */
public class PageTool<T> {

    private final List<T> all;
    private final Integer pageSize;

    private PageTool(List<T> all, Integer pageSize) {
        this.all = all;
        this.pageSize = pageSize;
    }

    public static <T> PageTool create(List<T> all, Integer pageSize) {
        return new PageTool<>(all, pageSize);
    }

    public List<T> get(Integer pageId) {
        if (pageId < 0 || pageSize < 0) {
            return Collections.emptyList();
        }
        int fromIndex = pageId * pageSize;
        int toIndex = fromIndex + pageSize;
        if (toIndex > all.size()) {
            toIndex = all.size();
        }
        return all.subList(fromIndex, toIndex);
    }

}
