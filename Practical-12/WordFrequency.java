import java.util.*;

public class WordFrequency {
    public static void main(String[] args)
    {
        String paragraph = "Java is fun and Java is powerful. Learning Java is easy when practice is regular and fun.";

        HashMap<String, Integer> freq = new HashMap<>();

        for(String w : paragraph.toLowerCase().split("\\W+")){
            if(!w.isEmpty()){
                freq.put(w, freq.getOrDefault(w, 0) + 1);
            }
        }

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());

        // highest frequency first, ties alphabetical
        list.sort(new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
                if(!a.getValue().equals(b.getValue())){
                    return b.getValue() - a.getValue();
                }
                return a.getKey().compareTo(b.getKey());
            }
        });

        for(Map.Entry<String, Integer> e : list){
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}