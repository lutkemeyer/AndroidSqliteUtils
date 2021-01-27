package main.android.database.query;

public class PaginationParams {

    private int limit = 0;
    private int pageNumber = 0;

    public PaginationParams(int limit, int pageNumber) {
        setLimit(limit);
        setPageNumber(pageNumber);
    }
    public PaginationParams() {
    }

    public int getLimit() {
        return limit;
    }
    public PaginationParams setLimit(int limit) {
        if(limit >= 0)
            this.limit = limit;
        return this;
    }
    public int getPageNumber() {
        return pageNumber;
    }
    public PaginationParams setPageNumber(int pageNumber) {
        if(pageNumber >= 0)
            this.pageNumber = pageNumber;
        return this;
    }
}
