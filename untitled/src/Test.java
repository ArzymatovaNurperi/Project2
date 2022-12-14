import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
public class Test {

public static void main(String[] args) {
    M m =new M();
    HashSet<M>hashSet=new HashSet<>();
    hashSet.add(m);

    System.out.println(hashSet.contains(m));

}

}class M{




    @Override
    public boolean equals(Object obj) {
       if (this==obj){
           return false;
       }
       else {
           return true;
       }
    }
}