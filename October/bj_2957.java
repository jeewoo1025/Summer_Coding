package basic;

import java.io.*;
import java.util.*;

public class bj_2957 {
    static int N;
    static long count=0;
    static int depth[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        depth = new int[N+2];

        // 초기화
        // TreeSet : 이진탐색트리, 작은 값은 왼쪽 자식으로, 큰 값은 오른쪽 자식으로
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0);
        treeSet.add(N+1);

        depth[0] = -1;
        depth[N+1] = -1;

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());

            // lower : 해당 객체 바로 아래에 있는 객체, higher : 바로 위에 있는 객체
            // 깊이가 더 깊은 수를 선택해 그 밑에 위치
            depth[num] = Math.max(depth[treeSet.lower(num)], depth[treeSet.higher(num)]) + 1;
            treeSet.add(num);

            // count update
            count += depth[num];
            sb.append(count + "\n");
        }

        System.out.println(sb);
    }
}
