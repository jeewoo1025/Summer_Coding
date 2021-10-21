package basic;

import java.io.*;
import java.util.*;

public class swea_19237 {
    static class Shark {
        int x,y; // 위치
        int dir;    // 방향
        int num;    // 번호
        int[][] priority; // 방향 우선순위 : 1~4번까지 (행: 방향, 열 : 방향의 우선순위)

        public Shark(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;

            this.priority = new int[5][4];
        }

        // 자기자신 - 남 : 오름차순
    }

    // 방향 : 위(1), 아래(2), 왼쪽(3), 오른쪽(4)
    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,-1,1};

    static int N,M,K;   // NxN 격자, M 상어 갯수, k: 냄새최대시간, time: 시간
    static int[][] board, smellTime;   // 번호 배열, 향기가 남아있는 시간
    static int count=0;    // 퇴출된 상어 갯수
    static boolean[][] isOn;    // 상어 있음 여부 (true: 있음 , false: 없음)
    static Shark[] sharks;  // 상어 리스트
    static final int MAX_TIME=1000; // 최대 시간

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        smellTime = new int[N][N];
        isOn = new boolean[N][N];
        sharks = new Shark[M+1];

        // 격자번호 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]>0) {  // 상어
                    sharks[board[i][j]] = new Shark(i,j,board[i][j]);
                    isOn[i][j] = true;
                    smellTime[i][j] = K;
                }
            }
        }

        // 각 상어의 방향 update
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }

        // 각 상어의 방향 우선순위
        for(int i=1; i<=M; i++) {
            // 4가지 방향의 우선순위 입력
            for(int j=1; j<=4; j++) {   // 위, 아래, 왼, 우
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {   // 각 방향의 우선순위
                    sharks[i].priority[j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int time = 0;
        while(time<=MAX_TIME) {
            // 1번 ~ M번까지 우선순위대로 방향 조회
            moveShark();

            // (0,0) ~ (N-1, N-1)까지 향기 시간 update & 상어 충돌 확인하기
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    // 상어 잇음 pass
                    if(isOn[i][j])
                        continue;

                    // 향기가 있으면 시간-1 and 0이면 없앰
                    if(smellTime[i][j]>0) {
                        if(--smellTime[i][j]<=0)
                            board[i][j] = 0;
                    }
                }
            }
            time++;

            // debug
//            System.out.println(time+"초 후 , 쫓겨난 상어갯수:"+count);
//            printBoard();

            // 퇴출된 상어 갯수 확인 --> 1개빼고 다 나갔니?
            if(count == (M-1))
                break;
        }

        // 시간 출력
        if(time>MAX_TIME)
            System.out.println(-1);
        else
            System.out.println(time);
    }

    public static void moveShark() {
        for(int i=1; i<=M; i++) {
            if(sharks[i] == null)
                continue;

            Shark now = sharks[i];
            boolean isGo = false;  // 이동가능한가? T: 이동가능, F : 이동 불가능

            // 현재 방향의 우선순위대로 방향 check
            List<Shark> tempList = new ArrayList<>();
            for(int d=0; d<4; d++) {
                int dir = now.priority[now.dir][d];  // 방향
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];

                // 이동 가능한가? 범위밖 & 상어있음 안됨
                if(nx<0 || nx>=N || ny<0 || ny>=N)
                    continue;
                if(isOn[nx][ny]) {  // 상어가 존재함
                    //System.out.println("상어가 존재함"+board[nx][ny]+"-"+smellTime[nx][ny]);
                    if(smellTime[nx][ny]==K && board[nx][ny]<now.num) { // 상어 있는데 우선순위가 낮음
                        // 이미 우선순위가 높은 상어가 차지함 --> 상어 퇴출
                        // 현재 상어 비우기
                        isOn[now.x][now.y] = false;
                        sharks[i] = null;

                        count++;        // 퇴출된 상어 갯수 증가
                        return;
                    } else {
                        continue;
                    }
                }
                if(smellTime[nx][ny]>0) {
                    if(board[nx][ny]==now.num) { // 번호가 같다면
                        tempList.add(new Shark(nx, ny, now.num));
                        tempList.get(tempList.size()-1).dir = dir;
                    }
                    continue;
                }

                // 이동가능함 --> 상어 위치 이동
                isOn[now.x][now.y] = false;
                sharks[i].x = nx;
                sharks[i].y = ny;
                sharks[i].dir = dir;

                isOn[nx][ny] = true;
                smellTime[nx][ny] = K;
                board[nx][ny] = now.num;

                isGo = true; // 이동함
                break;
            }

            // 이동 안 했을 경우 자신의 냄새있는 칸으로 이동
            if(!isGo) {
                // 자신의 냄새가 있는 칸으로 이동
                // 상어 위치 이동
                Shark obj = tempList.get(0);

                isOn[now.x][now.y] = false;
                sharks[i].x = obj.x;
                sharks[i].y = obj.y;
                sharks[i].dir = obj.dir;

                isOn[obj.x][obj.y] = true;
                smellTime[obj.x][obj.y] = K;
            }
        }
    }

    public static void printBoard() {
        char[] directions = {'↑','↓','←','→'};
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                // 상어 있니?
                if(isOn[i][j])
                    sb.append("상");

                // 향기 번호 - 시간
                sb.append("("+board[i][j]+"-"+smellTime[i][j]+") ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
