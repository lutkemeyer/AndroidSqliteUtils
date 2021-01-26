package main.android.database.query;

import java.util.ArrayList;
import java.util.List;

public class PaginatedList<T> {

    private int totalCount;
    private List<T> contentList;

    public PaginatedList() {
        this(0, null);
    }
    public PaginatedList(int totalCount, List<T> contentList) {
        this.totalCount = Math.max(totalCount, 0);
        this.contentList = contentList != null ? contentList : new ArrayList<>();
    }

    public int getTotalCount() {
        return totalCount;
    }
    public PaginatedList<T> setTotalCount(int totalCount) {
        if(totalCount >= 0)
            this.totalCount = totalCount;
        return this;
    }
    public List<T> getContentList() {
        return contentList;
    }
    public PaginatedList<T> setContentList(List<T> contentList) {
        if(contentList != null)
            this.contentList = contentList;
        return this;
    }
}
