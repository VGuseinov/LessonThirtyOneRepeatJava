package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        TODOList todoList = new TODOList();
        todoList.loadFromFile();
//        todoList.addDeal(date("13/10/2022"), new Deal("complete homework"));
//        todoList.addDeal(date("13/10/2022"), new Deal("complete study"));
//        todoList.addDeal(date("15/10/2022"), new Deal("преисполниться в Java!"));
//        todoList.addDeal(date("01/01/2025"), new Deal("bye Google and Amazon"));
        todoList.markDealAsCompleted(date("13/10/2022"), 2);
        todoList.showList();
        System.out.println("==========");
        todoList.showList(date("10/10/2022"));
        System.out.println("============");
        todoList.showListForWeek();
        todoList.saveToFile();

    }

    public static Date date(String d) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.parse(d);
    }
}
