import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book, (o1, o2) -> {
            return Integer.compare(o1.length(), o2.length());
        });
        
        HashSet<String> hs = new HashSet<>();
        for(int i=0;i<phone_book.length;i++){
            for(int j=1;j<phone_book[i].length()+1;j++){
                String temp = phone_book[i].substring(0, j);
                if(hs.contains(temp)){
                    return false;
                }
            }
            hs.add(phone_book[i]);
        }
        return true;
    }
}
