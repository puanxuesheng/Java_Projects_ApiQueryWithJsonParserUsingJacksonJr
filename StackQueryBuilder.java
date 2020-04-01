/*
This is the api query builder class.
This builder class will allow user to create a StackQuery object with the flexibility to
decide what properties needs to be changed. Else it will load the default values from here.
 */
package com.example.webjson;
import com.example.webjson.StackQuery.SortBy;
import com.example.webjson.StackQuery.SortOrder;

public class StackQueryBuilder {
    private String site = "stackoverflow";
    private int pageSize = 5;
    private StackQuery.SortOrder sortOrder = SortOrder.ASCENDING;
    private StackQuery.SortBy sortBy = SortBy.RELEVANCE;
    private String tag = "java";
    private String searchTerm = "";

    public StackQueryBuilder setSite(String site) {
        this.site = site;
        return this;
    }

    public StackQueryBuilder setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public StackQueryBuilder setSortOrder(StackQuery.SortOrder sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public StackQueryBuilder setSortBy(StackQuery.SortBy sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public StackQueryBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public StackQueryBuilder setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
        return this;
    }

    public StackQuery createStackQuery() {
        return new StackQuery(site, pageSize, sortOrder, sortBy, tag, searchTerm);
    }
}