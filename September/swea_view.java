package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class View {
    static int N;
    static int[] buildings;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int t=1; t<=10; t++) {
            // 입력
            N = Integer.parseInt(br.readLine());
            buildings = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                buildings[i] = Integer.parseInt(st.nextToken());
            }

            // 2 ~ N-3까지 반복
            int answer = 0, biggest;
            for(int x=2; x<=(N-3); x++) {
                // x-2 ~ x+2 중 x가 가장 크면
                biggest = Math.max(buildings[x-2], buildings[x-1]);
                biggest = Math.max(biggest, buildings[x+1]);
                biggest = Math.max(biggest, buildings[x+2]);

                // 차이 총 합에 더함
                if(buildings[x] > biggest) {
                    answer += (buildings[x]-biggest);
                }
            }
            sb.append(String.format("#%d %d\n", t, answer));
        }
        System.out.println(sb.toString());
    }
}
