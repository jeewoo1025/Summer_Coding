package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class swea_2517 {
    static class player implements Comparable<player> {
        int id, speed;

        public player(int id, int speed) {
            this.id = id;
            this.speed = speed;
        }

        @Override
        public int compareTo(player o) {  // 내림차순 정렬
            return o.speed - this.speed;
        }
    }

    private static int N, T;
    private static int[] indexedTree;
    private static player[] list; // 선수의 list
    private static int[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // list 정렬 시 0부터 전체 정렬을 위채 list[0]을 초기화
        list = new player[N+1];
        list[0] = new player(0,0);

        for(int i=1; i<=N; i++) {
            list[i] = new player(i, Integer.parseInt(br.readLine()));
        }

        // speed 기준 내림차순 정렬
        Arrays.sort(list);

        // 인덱스드트리 크기 특정 및 선택
        for(T=1; T<N; T*=2);
        indexedTree = new int[T*2+2];
        answer = new int[N+1];

        // speed가 가장 빠른 녀석부터 indexed tree 최초 순위 위치에 1로 갱신
        for(int i=0; i<N; i++) {
            player cur = list[i];

            // 지금 들어온 선수를 인덱스드 트리에 넣기
            edit(cur.id, 1);
            // System.out.print(String.format("edit(%d, %d)  ", cur.id, 1));

            // 1 ~ 최초 순위까지 이미 들어온 선수 count = 가능한 최고 순위
            // System.out.print(String.format("sum(%d, %d)   ", 1, cur.id));
            answer[cur.id] = sum(1, cur.id);
        }

        // 정답 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++)
            sb.append(answer[i]+"\n");
        System.out.println(sb.toString());
    }

    // 인덱스드트리 수정
    static void edit(int id, int value) {
        // 1) 해당 id값 수정
        int x = id+T-1;
        indexedTree[x] = value;

        // 2) 위로 올라가면서 값 갱신
        x /= 2;
        while(x>0) {
            indexedTree[x] = indexedTree[x*2]+indexedTree[x*2+1];  // 왼쪽 + 오른쪽
            x /= 2;
        }
    }

    // 인덱스드트리 합 구하기
    static int sum(int start, int end) {
        int left = start+T-1;
        int right = end+T-1;
        int ret = 0;

        while(left<=right) {
            // 왼쪽 id가 홀수이면 값이 튀므로 더해주고 left++ 해줌 (짝수부터 시작함)
            if(left%2==1)
                ret += indexedTree[left++];

            // 오른쪽 id가 짝수이면 값이 튀므로 더해주고 right--해줌 (홀수로 끝나야 하나까)
            if(right%2==0)
                ret += indexedTree[right--];

            // 위로 올라가기
            left /= 2;
            right /= 2;
        }
        // System.out.println(String.format("left : %d, right : %d, ret = %d", start+T-1, end+T-1, ret));
        return ret;
    }
}
