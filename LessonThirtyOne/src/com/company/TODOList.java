package com.company;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class TODOList {
    private static final String fileName = "ToDoList.bin";
    private HashMap<Date, LinkedList<Deal>> deals;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public TODOList() {
        this.deals = new HashMap<>();
    }

    public void addDeal(Date date, Deal deal) throws Exception {
        if (!this.deals.containsKey(date)) {
            LinkedList<Deal> linkedList = new LinkedList<>();
            linkedList.add(deal);
            this.deals.put(date, linkedList);
            return;
        }
        LinkedList<Deal> linkedList = this.deals.get(date);
        for(Deal d : linkedList) {
            if (d.name.equals(deal.name)) {
                throw new Exception("Deal is already exists");
            }
        }
        linkedList.add(deal);
    }

    public void showList() {
        for (Date d : this.deals.keySet()) {
            this.showList(d);
        }
    }

    public void showList(Date date) {
        LinkedList linkedList = this.deals.get(date);
        if (linkedList == null) {
            return;
        }
        System.out.println(this.sdf.format(date));
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, linkedList.get(i));
        }
    }

    public void showListForWeek() {
        Calendar calendar = Calendar.getInstance();
        int weekNow = calendar.get(Calendar.WEEK_OF_YEAR);
        int yearNow = calendar.get(Calendar.YEAR);
        for (Date date : this.deals.keySet()) {
            calendar.setTime(date);
            int week = calendar.get(Calendar.WEEK_OF_YEAR);
            int year = calendar.get(Calendar.YEAR);
            if (yearNow == year && weekNow == week) {
                this.showList(date);
            }
        }
    }

    public void markDealAsCompleted(Date date, int numberDeal) {
        LinkedList<Deal> linkedList = this.deals.get(date);
        if (linkedList == null) {
            return;
        }
        if (numberDeal <= 0 || numberDeal > linkedList.size()) {
            return;
        }
        Deal deal = linkedList.get(numberDeal - 1);
        deal.status = Status.DONE;
    }

    public void saveToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.deals);
        objectOutputStream.flush();
        fileOutputStream.close();
        objectOutputStream.close();
    }

    public void loadFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        this.deals = (HashMap<Date, LinkedList<Deal>>) objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();
    }
}
