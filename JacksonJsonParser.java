/*
This class is used for parsing the JSON stream into Java Bean objects.
 */

package com.example.webjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jr.ob.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JacksonJsonParser {

    public  List<QueryResultBean> parseJason(InputStream in) throws IOException {
//        List<QueryResultBean> test = JSON.std.listOfFrom(QueryResultBean.class,in);
//        return test;
        StackItems items = JSON.std.beanFrom(StackItems.class, in);
        
        
                //This is example for Jackson
//        ObjectMapper mapper = new ObjectMapper();
//        StackItems items = mapper.readValue(in,StackItems.class);
        
        return items.getItems();
    }
}
