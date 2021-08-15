import java.io.*;
import java.util.*;

class Solution {
    int N;  // 원의 갯수
    int r,l,b,t,dot;

    public int solution(int[] X, int[] Y, int[] R, int[] v) {
        N = X.length;

        for(int i=0; i<N; i++) {
            if(i==0) {
                l = X[i]-R[i];
                r = X[i]+R[i];
                b = Y[i]-R[i];
                t = Y[i]+R[i];
                continue;
            }

            // l : x좌표 - 반지름에서 최소값, r : x좌표 + 반지름에서 최댓값
            l = Math.min(l, X[i]-R[i]);
            r = Math.max(r, X[i]+R[i]);

            // b : y좌표 - 반지름에서 최소값, t : y좌표 + 반지름에서 최댓값
            b = Math.min(b, Y[i]-R[i]);
            t = Math.max(t, Y[i]+R[i]);
        }

        //System.out.println("정답 (l,t) : "+l+","+t+",  (r,b) : "+r+","+b);

        // 3. 난수 x,y 구하기
        for(int j=0; j<(v.length-1); j+=2) {
            // 4. 좌표 변환하기
            int tmp_x = l + v[j] % (r - l);
            int tmp_y = b + v[j+1] % (t - b);

            //System.out.print("random "+tmp_x+","+tmp_y);

            // 5. 원 포함 O --> dot 갯수 증가
            for(int k=0; k<N; k++) {
                if(isIn(tmp_x, tmp_y, X[k], Y[k], R[k])) {
                    dot++;
                    //System.out.print(" --> 원 내부에 있음!\n");
                    break;
                }
            }
            //System.out.println();
        }

        return (dot*(r-l)*(t-b))/(v.length/2);
    }

    public boolean isIn(double x, double y, int X, int Y, int R) {
        double val = (x-X)*(x-X)+(y-Y)*(y-Y);

        // 원 밖이면
        if(val > R*R)
            return false;

        return true;
    }
}