package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class swea_2477_ver2 {
    static int a[], b[], t[], N, M, K, A, B, answer;
    static int timeA[], userA[], timeB[], userB[];
    static boolean isDone;
    static HashMap<Integer, List<Integer>> arrivedTimes; // key: 도착시간, value : 도착한 번호들
    static PriorityQueue<Integer> aWaitingList = new PriorityQueue<>();  // 접수 waiting list (min heap)
    static Queue<Integer> bWaitingList = new LinkedList<>();  // 정비 waiting list (queue)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // 목적 : 지갑을 분실한 고객과 같은 접수 창구과 같은 정비 창구를 이용한 고객의 고객번호를 찾아 그 합을 출력해라!

        arrivedTimes = new HashMap<>();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());  // 접수 창구번호
            B = Integer.parseInt(st.nextToken());  // 정비 창구번호

            // 초기화
            a = new int[N+1];
            timeA = new int[N+1];
            userA = new int[N+1];

            b = new int[M+1];
            timeB = new int[M+1];
            userB = new int[M+1];

            answer = 0;
            isDone = false;

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=A; i++)
                a[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=B; i++)
                b[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++) {
                int time = Integer.parseInt(st.nextToken());

                // arrivedTime[도착시간] = 번호
                if(arrivedTimes.containsKey(time))
                    arrivedTimes.get(time).add(i);
                else
                    arrivedTimes.put(time, new ArrayList<>(List.of(i)));
            }

            // 시간
            int time = 0;

            while(true) {
                // 접수 waiting list update
                update_aWaitingList(time);

                // time_a, user_a update
                List<Integer> temp = update(N, A, a, userA, timeA, aWaitingList);

                // 정비 예비 리스트를 오름차순으로 정렬
                Collections.sort(temp);
                bWaitingList.addAll(temp);

                // time_b, user_b update
                update(M, B, b, userB, timeB, bWaitingList);
                if(check())
                    break;

                time += 1;
            }

            sb.append("#"+tc+" "+answer+"\n");
        }
        System.out.println(sb.toString());
    }

    // 끝
    private static boolean check() {
        for(int i=1; i<=N; i++) {
            if(timeA[i] > 0)
                return false;
        }

        if(!bWaitingList.isEmpty())
            return false;

        return true;
    }

    private static List<Integer> update(int limit, int obj, int[] origin, int[] users, int[] times, Queue<Integer> queue) {
        List<Integer> results = new ArrayList<>();

        for(int i=1; i<=limit; i++) {
            if(users[i]>0) {
                times[i] -= 1;
                if(times[i] == 0) {
                    results.add(users[i]);

                    if(queue.isEmpty()) {
                        users[i] = 0;
                    }
                    else {
                        users[i] = queue.poll();
                        // 만약 목적번호랑 같다면
                        if(users[i] == obj)
                            answer += users[i];

                        times[i] = origin[i];
                    }
                }
            }
        }

        return results;
    }

    private static void update_aWaitingList(int time) {
        // 현재 time에 있는 번호들 불러오기
        List<Integer> cur = arrivedTimes.get(time);
        if(cur == null)
            return;

        // 번호들을 접수 대기 리스트에 추가
        for(Integer idx:cur)
            aWaitingList.add(idx);
    }
}
