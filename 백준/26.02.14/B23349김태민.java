package daily2601;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 조건
 *  - 이름: 영어, 한 학생이 여러번 제출하면 첫 번째 제출 이외에는 무시
 *  - 장소: 영어
 *  - 시간대: 촬영시작시간, 종료 시간
 *  혼잡 장소와 시간대 공지하는법
 *  - 1. 가장 많은 사람이 제출한 (장소, 시간대) 쌍을 선택
 *  - 2. 1번에 해당하는 구간이 여러개면 아스키 코드 순으로
 *  - 3. 2번에서 시간대가 여러개라면 가장 빠른 시간대로
 *
 * 설계
 *  - 해시 사용하면 될것 같음.(사용 안해도 될듯 그냥 이름은 그저 중복 방지용)
 *  - 이름이 중복되면 넣지도 못하게
 *  - 그런 다음 정렬시키면 끝일지도?
 *  - 처음에 값을 받을 때만 해시 사용하고
 *  - 각 장소별로 구간이 얼마나 겹치는지 판단해야함
 *  - 그 후에 겹치는 구간이 동일하면 이름 순으로
 *  - 구간은 제일 앞에서 부터
 *  - 구간은 우선순위 큐로 시작 시간을 기준으로 정렬
 *
 *  - 두 번쨰의 시작시간과 첫 번쨰의 종료시간을 구간 저장
 *  ---> 쭉쭉 이어 나가면 될듯?
 *
 *
 */

public class Main23349졸업사진 {

    static class Node implements Comparable<Node>{
        String place;
        int start;

        public Node(String place, int start){
            this.place = place;
            this.start = start;
        }


        @Override
        public int compareTo(Node o) {
            if(!this.place.equals(o.place)){
                return this.place.compareTo(o.place);
            }
            return Integer.compare(this.start,o.start);
        }
    }
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        HashMap<String, TreeMap<Integer, Integer>> map = new HashMap<>();
        Set<String> set = new HashSet<>(); // 사람 이름 중복 방지


        int max = -1;   // 어느 구간에 사람이 제일 많은지 판단임

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String place = st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());


            if(set.contains(name)){
                continue;
            }
            set.add(name);

            TreeMap<Integer, Integer> times = map.computeIfAbsent(place,k -> new TreeMap<>());
            for(int time = start; time<end; time++){
                times.put(time, times.getOrDefault(time,0)+1);
                max = Math.max(max, times.get(time)); // 어느 구간에 사람이 제일 많은지 저장
            }
        }

        List<Node> maxVisit = new ArrayList<>();

        // 가장 많이 방문한 장소와 구간 찾기, 그래서 쌍으로 maxVisit에 저장
        // 시작 시간만 찾으면 됨 그다음은 돌리면서 end 시간을 찾으면 됨.
        for(String place: map.keySet()){
            TreeMap<Integer, Integer> times = map.get(place);
            for(Map.Entry<Integer,Integer> timeEntry: times.entrySet()){
                int time = timeEntry.getKey();
                if(timeEntry.getValue() == max){
                    maxVisit.add(new Node(place,time));
                    break;
                }
            }
        }
        Collections.sort(maxVisit);

        // 정렬하면 장소와 시간이 알아서 정렬됨
        Node bestTime = maxVisit.get(0);
        int endTime = findEnd(map.get(bestTime.place), bestTime.start, max);
        System.out.println(bestTime.place+" "+ bestTime.start+" "+endTime);

    }
    static int findEnd(TreeMap<Integer, Integer> times, int start, int max){
        int end = start;
        while (times.containsKey(end)&&times.get(end)==max){
            end++;
        }
        return end;
    }
}










