package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class swea_1952 {
    static int dayFee, monthFee, threeMonthFee, yearFee;  // 요금
    static int answer;
    static int[] schedule = new int[13];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            dayFee = Integer.parseInt(st.nextToken());
            monthFee = Integer.parseInt(st.nextToken());
            threeMonthFee = Integer.parseInt(st.nextToken());
            yearFee = Integer.parseInt(st.nextToken());

            // 초기화
            answer = yearFee;

            //1월 ~ 12월
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=12; i++) {
                schedule[i] = Integer.parseInt(st.nextToken());
            }

            // 최소 비용 구하기
            dfs(1, 0);
            sb.append("#"+t+" "+answer+"\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int month, int total) {
        if(month == 13) {
            answer = answer>total ? total:answer;
            return;
        }

        // 이용 안 할 때 안사기
        if(schedule[month] == 0)
            dfs(month+1, total);

        // 1일 사용권으로 채우기 : 이용횟수 >= 1
        if(schedule[month] > 0)
            dfs(month+1, total+schedule[month]*dayFee);

        // 1달 사용권으로 채우기
        if(schedule[month] > 0)
            dfs(month+1, total+monthFee);

        // 3달 사용권으로 채우기 10월까지만 구매 가능
        if(month <= 10)
            dfs(month+3, total+threeMonthFee);
    }
}
