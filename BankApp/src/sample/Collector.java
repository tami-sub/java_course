package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Collector implements Serializable {
    private List<Account> comparison = new ArrayList<>();
    private List<List<Object>> allTransactions = new ArrayList<>();
    public Collector(List<Account> comparison, List<List<Object>> transaction){
        this.allTransactions=transaction;
        this.comparison=comparison;
    }

    public Map<Integer, String> getTransactions() {
        List<List<Object>> newone = new ArrayList<>();

        for (List<Object> allTransaction : allTransactions)
            newone.add((List<Object>) ((ArrayList) allTransaction).clone());

        for (List<Object> el : newone) {
            IntStream.range(0, el.size()).filter(i -> i % 2 == 1).forEachOrdered(i -> {
                Account temp = (Account) el.get(i);
                el.set(i, temp.getFullFIO());
            });
        }


        Map<Integer, String> table = new HashMap<>();
        for (int i = 0; i < newone.size(); i++) {
            table.put(i, String.format("Balance after: %s\t Sender: %s\t Amount: %s\t Recipient:%s\t Balance after: %s\n",
                    newone.get(i).get(0), newone.get(i).get(1), newone.get(i).get(2),
                    newone.get(i).get(3), newone.get(i).get(4)));
        }
        return table;
    }

    public Map<Integer, String > pivotTable(){
        Map<Integer, String> table = new HashMap<>();
        for (int i = 0; i < comparison.size(); i++) {
            Account comp = comparison.get(i);
            table.put(i, String.format("Owner: %s\t Balance: %s", comp.getFullFIO(), comp.getBalance()));
        }
        return table;
    }
}
