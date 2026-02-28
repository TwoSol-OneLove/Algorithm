import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int times = 0;  // 걸린 시간 
    
        int currentWeight = 0;  // 현재 다리의 무게 
        Queue<Integer> bridge = new LinkedList<>(); // 다리 (각 노드는 1초 차이)
        
        for(int i=0;i<truck_weights.length;i++){
            int truck = truck_weights[i];
            
            while(true){
                if(bridge.size() == bridge_length){ // 다리가 가득 찬 경우 
                    currentWeight-=bridge.peek();
                    bridge.poll();
                } else {    // 다리가 가득 차지 않은 경우 
                    if(currentWeight + truck <= weight){    // 바로 다음 트럭을 올릴 수 있는 경우 
                        currentWeight+=truck;
                        bridge.add(truck);
                        times++;
                        break;
                    }else{      // 바로 다음 트럭을 올릴 수 없는 경우 
                        bridge.add(0);  // 빈칸을 올림 
                        times++;        // 1초 경과 시킴 
                    }
                }
            }
        }
        // 마지막 트럭까지 다리에 올라가는데 걸린 시간 + 마지막 트럭이 다리를 완전히 건너는 시간 
        return times+bridge_length; 
    }
}
