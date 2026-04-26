import java.util.*;

class Solution {
    static HashMap<Integer, Integer> hm;
    static PriorityQueue<Info> q;
    
    static class Info implements Comparable<Info>{
        int size;
        int cnt;
        
        Info(int size, int cnt){
            this.size = size;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Info next){
            return Integer.compare(next.cnt, this.cnt);
        }
    
    }
    
    public int solution(int k, int[] tangerine) {
        
        hm  = new HashMap<Integer, Integer>();
        q = new PriorityQueue<>();
        
        for(int i=0;i<tangerine.length;i++){
            if(hm.containsKey(tangerine[i])){
                int cnt = hm.get(tangerine[i]);
                hm.put(tangerine[i], cnt+1);
            }
            else{
                hm.put(tangerine[i], 1);
            }
        }
        
        for(Integer key : hm.keySet()){
            int cnt = hm.get(key);
            q.add(new Info(key, cnt));
        }
        
        int result = 0;
        while(k>0){
            Info info = q.poll();
            result++;
            k-= info.cnt;
        }
        
        return result;
    }
}
