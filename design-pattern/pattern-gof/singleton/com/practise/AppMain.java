package com.practise;

import java.io.*;

public class AppMain {

    public static void main(String arg[]) throws IOException, ClassNotFoundException {
        System.out.println("Hellow");

        /** handle clone issue **/
        try {
            DateUtil.getInstance().clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("exception : " + e.getMessage());
        }

        /** handle serialization issue in singleton **/
        DateUtil dateUtil = DateUtil.getInstance();
        ObjectOutputStream  oos = new ObjectOutputStream(new FileOutputStream(new File("C:\\Users\\Other\\Documents\\design-patterns\\dateUtil.ser")));
        oos.writeObject(dateUtil);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("C:\\Users\\Other\\Documents\\design-patterns\\dateUtil.ser")));
        DateUtil dateUtil2 = (DateUtil) ois.readObject();

        oos.close();
        ois.close();

        // this will true since we have resolved the serialization issue.
        System.out.println(dateUtil == dateUtil2);
    }
}
