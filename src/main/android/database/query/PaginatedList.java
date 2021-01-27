package main.android.database.query;

import java.util.ArrayList;
import java.util.List;

public class PaginatedList<T> {

    private int totalCount = 0;
    private List<T> contentList = new ArrayList<>();

    public PaginatedList() {
    }
    public PaginatedList(int totalCount, List<T> contentList) {
        setTotalCount(totalCount);
        setContentList(contentList);
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
