package daily2602;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 조건
 *  - Si로 시작해서 Ti에 끝나는 N개의 수업이 있음
 *  - 최소의 강의실을 사용해서 모든 수업을 가능하게 해야함
 *  - 필요한 최소 강의실 갯수를 구하기
 *
 *
 * 설계
 *  - Ti를 기준으로 우선순위 정렬하기
 *  - 다음 수업시간표을 봤을때 기존에 있던 Ta<=Sb이면 같은 강의실 사용 가능
 *  - Ta>Sb이면 새로운 강의실을 파야함
 *  - 근데 매번 모든 강의실을 하나씩 돌면서 파악해야하나? 이러면 너무 오래 걸릴것 같은데
 *  - 강의실을 사용하고 있는 곳도 우선순위큐로 관리하면 좋을듯?
 *  - 그래서 서로 하나씩 뺴면서 Sb보다 작은 Ta들은 다 뺴고 Sb를 우큐에 넣기
 *  - Ta>Sb이면  카운트 +1 해주고 우큐에 추가 굳굳
 *
 *
 *
 */


public class B11000_김태민 {

    static class StudyRoom implements Comparable<StudyRoom>{
        int start;
        int end;

        public StudyRoom(int start, int end){
            this.start = start;
            this.end = end;

        }


        @Override
        public int compareTo(StudyRoom o) {
            if(this.start==o.start){
                return Integer.compare(this.end, o.end);
            }
            return Integer.compare(this.start, o.start);
        }
    }



    static int n;
    static PriorityQueue<StudyRoom> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.add(new StudyRoom(a,b));
        }
        int result = Solution();
        System.out.println(result);

    }

    static int Solution(){
        PriorityQueue<Integer> study = new PriorityQueue<>();
        int max = 0;

        while (!pq.isEmpty()){
            StudyRoom sr = pq.poll();

            if(!study.isEmpty() && study.peek() <= sr.start){
                study.poll();
            }
            study.add(sr.end);

            max = Math.max(max, study.size());
        }
        return max;
    }

}
