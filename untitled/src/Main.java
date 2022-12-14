import java.util.*;

public class Main {
    public static void main(String[] args) {
        put(500, 2l);
        put(200, 1l);
        put(100,1l);
        System.out.println(get(1000l));

    }

    static Map<Integer, Long> cashes = new TreeMap<>(Collections.reverseOrder());

    static Map<Integer, Long> put(Integer bill, Long count) {

        if (cashes.containsKey(bill)) {
            count = cashes.get(bill) + count;
            cashes.put(bill, count);
        }else {
            cashes.put(bill, count);
        }
        return cashes;
    }

    static Map<Integer, Long> get(Long summa) {
        Map<Integer, Long> answer = new HashMap<>();
        LinkedList<Integer>bills=new LinkedList<>();
        Long res = 0l;
        for (Map.Entry<Integer, Long> pair : cashes.entrySet()) {
            int bill = pair.getKey();
            Long count = pair.getValue();
            bills.add(pair.getKey());
            long newCount = 0;
            for (int i = 0; i < count; i++) {
                if (summa >= res + bill || bills.getLast()<=res) {
                    newCount++;
                    answer.put(bill, newCount);
                    res += bill;
                }
                else {
                    break;
                }
            }

        }
        return answer;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}