package org.example.microservices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryDb {
    private Map<Long, Data> recordMap;


    public InMemoryDb() {
        recordMap = new HashMap<>();
    }

    public void addData(Data data){
        recordMap.put(data.getAccount(), data);
    }

    public void removeData(Data data){
        recordMap.remove(data.getAccount());
    }

    public void changeData(Data data){
        if (recordMap.containsKey(data.getAccount())) {
            recordMap.put(data.getAccount(), data);
        }
        else {
            System.out.println("Данной записи нет");
        }
    }

    public List<Data> findByValue(double value){
       return recordMap.values().stream().filter(p->p.getValue()==value).collect(Collectors.toList());
    }

}
