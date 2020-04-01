package com.example.webjson;

import java.util.List;

//use the json ignore properties and set unknown to be true. Only apply to Jackson not Jackson jr
@JsonIgnoreProperties(ignoreUnknown = true)
public class StackItems {
    private List<QueryResultBean> items;

    public List<QueryResultBean> getItems() {
        return items;
    }
    public void setItems(List<QueryResultBean> items) {
        this.items = items;
    }
}
