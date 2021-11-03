package basic;

import java.io.*;
import java.util.*;

public class bj_1517 {
    static int N;
    static long[] num, numCopy;
    static long count=0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        num = new long[N];
        numCopy = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            num[i] = Integer.parseInt(st.nextToken());

        mergeSort(0, N-1);
        System.out.println(count);
    }

    public static void mergeSort(int p, int r) {
        if(p < r) {
            int q = (p+r)/2;  // 중간 지점
            mergeSort(p, q);  // 전반부 정렬
            mergeSort(q+1, r);  // 후반부 정렬
            merge(p, r);  // 합침
        }
    }

    public static void merge(int left, int right) {
        int mid = (left + right)/2;
        int i = left;
        int j = mid+1;
        int k = left;

        while(i<=mid && j<=right) {
            if(num[i] > num[j]) {
                numCopy[k++] = num[j++];
                count += (mid-i+1);
            } else
                numCopy[k++] = num[i++];
        }

        if(i>mid) {  // 오른쪽 배열이 남은 경우
            while(j<=right)
                numCopy[k++] = num[j++];
        } else { // 왼쪽 배열이 남은 경우
            while(i<=mid)
                numCopy[k++] = num[i++];
        }

        // update
        for(int z=left; z<=right; z++)
            num[z] = numCopy[z];
    }
}
