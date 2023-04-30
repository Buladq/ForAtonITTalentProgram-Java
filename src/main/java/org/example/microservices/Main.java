package org.example.microservices;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InMemoryDb db = new InMemoryDb();
        Data data1=new Data(567890L, "Петров Петр Петрович", 1500.0);
        Data data2=new Data(563290L, "Иванов Иван Иванович", 2300.0);

// добавление записей

        db.addData(data1);
        db.addData(data2);


        for (var i:
             db.findByValue(2300.0)) {
            System.out.println(i);
        }

        Data data3=new Data(56223290L, "Иванов Иван ИвановичN", 2300.0);
        db.changeData(data3);

        for (var i:
                db.findByValue(2300.0)) {
            System.out.println(i);
        }

    }
}