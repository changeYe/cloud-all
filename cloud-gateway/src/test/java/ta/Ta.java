package ta;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yuantongqin
 * description:
 * 2020/4/30
 */

public class Ta {

    @Test
    public void aa(){
        System.out.println("aaa");

        Date date = new Date(1588920522675L);
        Date date1 = new Date(1588918200405L);
        System.out.println(date);
        System.out.println(date1);


        String s = null;
        switch (s){
            case "a":
                System.out.println("aa");
                break;
        }

        Hashtable<String,String> ht = new Hashtable<>();

        System.out.println(ht.size());

    }

    @Test
    public void ab(){


        List<String> a = new ArrayList<>(10);
        a.add("a");
        a.add("b");
        a.add("c");
        a.stream().map(info->{return info+"";}).collect(Collectors.toMap(String::hashCode, new Function<String, Object>() {
            @Override
            public Object apply(String s) {

                return null;
            }
        }, (v1, v2) -> v2));

        HashMap<String,String> aMap = new HashMap<>();
        aMap.put("","");

        ConcurrentHashMap<String,String> chm = new ConcurrentHashMap<>();
        chm.put("","");

        Hashtable<String,String> ht = new Hashtable<>();
        ht.put(null,"");

//        Executors.newScheduledThreadPool()



    }



}
