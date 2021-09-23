package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_2477 {
    static int a[], b[], t[], N, M;
    static int timeA[], userA[], timeB[], userB[];
    static PriorityQueue<Integer> aWaitingList = new PriorityQueue<>();  // 접수 waiting list (min heap)
    static Queue<Integer> bWaitingList = new LinkedList<>();  // 정비 waiting list (queue)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 목적 : 지갑을 분실한 고객과 같은 접수 창구과 같은 정비 창구를 이용한 고객의 고객번호를 찾아 그 합을 출력해라!

        // 접수 창구
        // case 1. 고객번호가 낮은 순서대로 우선 접수한다.
        // case 2. 빈 창구가 여러 곳인 경우 창구번호가 작은 곳으로 간다.


        // 정비 창구
        // case 1. 먼저 기다리는 고객이 우선 접수.
        // case 2. 두 명 이상의 고객들이 접수 창구에서 동시에 접수를 완료하고 정비 창구로 이동한 경우,
        //  이용했던 접수 창구번호가 작은 고객이 우선한다. -> queue에 여러개 넣을 때 오름차순 정렬 뒤 넣기
        // case 3. 빈 창구가 여러 곳인 경우 창구번호가 작은 곳으로 간다.
    }
}