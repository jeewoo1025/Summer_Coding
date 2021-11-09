import java.io.*;
import java.util.*;

public class bj_2467 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int min = Integer.MAX_VALUE;
        int L=0, R=0;

        for(int i=0; i<N; i++) {
            // 자신 기준 오른쪽 용액을 대상으로 이분탐색
            int left=i+1, right=N-1, target=arr[i];

            while(left<=right) {
                int mid = (left+right)/2;
                int sum = Math.abs(target + arr[mid]);

                if(min > sum) {
                    min = sum;
                    L = target;
                    R = arr[mid];
                }

                // 자신의 음의 값과 가장 가까운 위치를 찾는다면 0과 근사한 결과를 얻음
                if(arr[mid] == -target)
                    break;
                else if(arr[mid] < -target)
                    left = mid+1;
                else
                    right = mid-1;
            }
        }
        System.out.println(L + " " + R);
    }
}
