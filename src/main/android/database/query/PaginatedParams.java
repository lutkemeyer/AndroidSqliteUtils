package main.android.database.query;

public class PaginatedParams {

    private int limit;
    private int pageNumber;

    public PaginatedParams(int limit, int pageNumber) {
        this.limit = limit;
        this.pageNumber = pageNumber;
    }
    public PaginatedParams() {
        this(0, 0);
    }

    public int getLimit() {
        return limit;
    }
    public PaginatedParams setLimit(int limit) {
        this.limit = limit;
        return this;
    }
    public int getPageNumber() {
        return pageNumber;
    }
    public PaginatedParams setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }
}
