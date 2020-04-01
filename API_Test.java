/*
* Please include the following libraries for this application to work
* Jackson-jr
* Jackson-databind
* Javax.Jason-api
*
* The test API users the builder class to set the inputs
* */



package com.example.webjson;

import com.example.webjson.StackQuery.SortOrder;
import com.example.webjson.StackQuery.SortBy;

import java.io.IOException;
import java.util.List;

public class API_Test {

    public static void test() throws IOException {
        //using a builder to build the class
        StackQuery query = new StackQueryBuilder()
                .setSearchTerm("helloworld").setPageSize(2).setSortOrder(SortOrder.ASCENDING)
                .setSortBy(SortBy.RELEVANCE).setTag("java")
                .createStackQuery();

        //After setting input, connect to the server
        List<QueryResultBean> result =  query.executeJson();
        for(QueryResultBean T : result)
        {
            System.out.println(T.getOwner().getDisplay_name());
        }

        //////////////////////////////////////////////////////////////////////////////////////
//        String resultString = query.executeString();
//        System.out.println(resultString);
//        query.setSearchTerm("helloworld");
//        query.setPageSize(1);
//        query.setSortBy(SortBy.RELEVANCE);
//        query.setSortOrder(SortOrder.ASCENDING);
//        query.setTag("java");
    }

    public static void main(String[] args)
    {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



