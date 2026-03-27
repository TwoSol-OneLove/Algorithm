/**
조건
 - 붕대감기: t초 동안 붕대를 감으면서 1초 마다 x 만큼 체력을 회복
        t초 연속으로 붕대를 감으면 y만큼 더 추가 회복함
 - 공격 당하거나 기술이 끝나면 다시 기술(붕대감기)를 사용함
 - 체력이 0이하가 되면 캐릭터는 죽음
 - bandage: 시전시간, 초당 회복량, 추가 회복량
 - attacks: 공격시간, 피해량

설계
 - 매초당 판별하면서 해당 초에 공격이 없으면 체력을 회복하고, 연속 스택을 쌓음
    if(시전 시간동안 회복하면) -> y만큼 추가 회복
 - 공격이 있으면 스택은 초기화, 해당 턴에 체력 회복은 불가, 데미지 만큼 체력이 감소
 - 사실상 첫공격이후 부터 계산하는게 맞음


*/

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int length = attacks.length;
        int time = 0;  // 시간 변수
        int count = 0;  // 연속되는지 보는 함수
        int initHealth = health;
        
        
        int answer = 0;
        
        for(int i = 0; i<length; i++){
            int nowTimes = attacks[i][0];
            int damage = attacks[i][1];
            if(i ==0){
                health -=damage;
            } else {
                int totalTime = nowTimes - time-1;
                health = health+ totalTime*bandage[1]+(totalTime/bandage[0])*bandage[2];
                if(health>initHealth) health = initHealth;
                health -= damage;
            }
            if(health<=0) {
                health = -1;
                break;
            }
            
            time = nowTimes;

        }
        
        return health;
    }
}