package basic;

import java.io.*;
import java.util.*;

public class bj_2751 {
    static int N;
    static int arr[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        // 입력
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        mergeSort(arr, 0, N-1);

        // 출력
        for(int i=0; i<N; i++)
            sb.append(arr[i] + "\n");
        System.out.println(sb);
    }

    public static void mergeSort(int[] data, int p, int r) {
        if(p < r) {
            int q = (p+r)/2;  // 중간 지점
            mergeSort(data, p, q);  // 전반부 정렬
            mergeSort(data, q+1, r);  // 후반부 정렬
            merge(data, p, q, r);  // 합침
        }
    }

    public static void merge(int[] data, int p, int mid, int r) {
        int i=p, j=mid+1, k=p;
        int[] temp = new int[data.length];

        while(i<=mid && j<=r) {
            if(data[i] <= data[j])
                temp[k++] = data[i++];
            else
                temp[k++] = data[j++];
        }

        while(i<=mid)
            temp[k++] = data[i++];
        while(j<=r)
            temp[k++] = data[j++];

        System.arraycopy(temp, p, data, p, (r-p)+1);
    }
}
