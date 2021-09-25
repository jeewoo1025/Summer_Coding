package basic;

import java.io.*;
import java.util.*;

public class swea_2477 {
    private static class Person implements Comparable<Person> {
        int idx, arriveTime, waitTime, infoNum, fixNum;

        public Person(int idx, int arriveTime) {
            this.idx = idx;  // 고객번호
            this.arriveTime = arriveTime;  // 도착시간
            this.waitTime = 0;  // 기다린 시간
            this.infoNum = 0;  // 접수 창구번호
            this.fixNum = 0; // 정비 창구번호
        }

        @Override
        public int compareTo(Person o) {  // 먼저 기다리는 고객이 우선
            int num = this.waitTime - o.waitTime;
            if(num==0)  // 접수 창구번호가 작은 고객 우선
                num = this.infoNum - o.infoNum;
            return num;
        }
    }

    static int N, M, K, A, B, infoTime[], fixTime[];
    static Person[] infoList, fixList;
    static Queue<Person> infoWait, tempWait, endPerson;  // 접수 waiting list
    static PriorityQueue<Person> fixWait;  // 정비 waiting list

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            // 초기화
            infoWait = new LinkedList<>();
            tempWait = new LinkedList<>();
            endPerson = new LinkedList<>();
            fixWait = new PriorityQueue<>();

            // 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());  // 접수 창구번호
            B = Integer.parseInt(st.nextToken());  // 정비 창구번호

            // 접수창구 처리시간
            infoTime = new int[N];
            infoList = new Person[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++)
                infoTime[i] = Integer.parseInt(st.nextToken());

            // 정비 창구 처리시간
            fixTime = new int[M];
            fixList = new Person[M];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++)
                fixTime[i] = Integer.parseInt(st.nextToken());

            // 방문한 고객 도착시간
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++)
                infoWait.add(new Person(i, Integer.parseInt(st.nextToken())));

            int time = 0;
            while(true) {
                // 접수, 정비 창구 모두 비었니 여부? true : empty, false : not empty
                boolean isEmpty = true;

                // 접수 창구
                for(int i=0; i<N; i++) {
                    if(infoList[i] == null) {
                        if(infoWait.isEmpty())
                            continue;

                        Person p = infoWait.peek();
                        if(time >= p.arriveTime) {
                            p = infoWait.poll();
                            p.waitTime = 1;
                            p.infoNum = i;
                            infoList[i] = p;
                        }
                    } else {
                        infoList[i].waitTime++;
                    }
                    isEmpty = false;

                    // 시간이 다 됨
                    if(infoList[i] != null && infoList[i].waitTime == infoTime[i]) {
                        infoList[i].waitTime = time;
                        tempWait.add(infoList[i]);
                        infoList[i] = null;
                    }
                }

                // 정비 창구
                for(int j=0; j<M; j++) {
                    if(fixList[j] == null) {
                        if(fixWait.isEmpty())
                            continue;

                        Person p = fixWait.poll();
                        p.waitTime = 1;
                        p.fixNum = j;
                        fixList[j] = p;
                    } else {
                        fixList[j].waitTime++;
                    }
                    isEmpty = false;

                    // 시간이 다 됨
                    if(fixList[j] != null && fixList[j].waitTime == fixTime[j]) {
                        endPerson.add(fixList[j]);
                        fixList[j] = null;
                    }
                }

                // 종료조건 : 접수, 정비 창구 모두 비고, 대기줄까지 비면 계속 true로 유지됨
                if(infoWait.isEmpty() && fixWait.isEmpty() && isEmpty)
                    break;

                // 접수 창구에서 나온 애들 정비창구 대기줄에 넣어주기
                while(!tempWait.isEmpty())
                    fixWait.add(tempWait.poll());

                time++;
            }

            // 끝난 사람들 check
            int answer = 0;
            while(!endPerson.isEmpty()) {
                Person p = endPerson.poll();
                if(p.infoNum==(A-1) && p.fixNum==(B-1))
                    answer += p.idx;
            }

            if(answer == 0)
                answer = -1;
            sb.append("#"+tc+" "+answer+"\n");
        }
        System.out.println(sb);
    }
}
