import java.util.*;

class Solution {
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<attacks.length;i++){
            queue.add(attacks[i]);
        }
        
        int currentTime=1;  // 현재 시간 
        int lastTime=0;     // 붕대를 감은 연속 시간 
        int currentHealth=health;   // 현재 체력
        
        while(!queue.isEmpty()){
            // 체력이 0 이하가 된 경우 
            if(currentHealth<=0){
                return -1;
            }
            
            int[] attack = queue.peek();
            if(attack[0]>currentTime){      // 공격이 안 들어왔을 때 -> 붕대 감기 
                currentHealth = Math.min(health, currentHealth+bandage[1]);
                lastTime++;
                if(lastTime==bandage[0]){   // 연속 t 시간 동안 붕대 감았을 경우 -> 보너스
                    currentHealth = Math.min(health, currentHealth+bandage[2]);
                    lastTime = 0;
                }
            }else if(attack[0]==currentTime){   // 공격이 들어왔을 때
                queue.poll();
                currentHealth-=attack[1];
                lastTime=0;
            }
            currentTime++;
        }
                
        return currentHealth<=0?-1:currentHealth;
    }
}
