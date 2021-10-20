package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_19236_answer {
    static class Fish {
        int num,x,y,dir,alive;

        // alive : 0(죽음), 1(살음)
        public Fish(int num, int x, int y, int dir, int alive) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.alive = alive;
        }
    }

    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[][] map;  // 맵에는 물고기 번호가 저장됨
    static Fish[] fish; // 물고기 정보 저장
    static int answer=0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[4][4];
        fish = new Fish[16+1];

        // 물고기 입력
        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                int num = Integer.parseInt(st.nextToken());  // 번호
                int dir = Integer.parseInt(st.nextToken())-1;  // 방향

                fish[num] = new Fish(num, i, j, dir, 1);
                map[i][j] = num;
            }
        }

        // 시작 : 상어가 (0,0) 물고기 먹음
        int sx=0, sy=0;     // 상어의 위치
        int sd = fish[map[0][0]].dir;   // 초기 상어의 방향
        int eat = map[0][0];    // 먹은 물고기의 합
        fish[map[0][0]].alive = 0;  // 물고기 죽음
        map[0][0] = -1;     // 상어의 위치

        // DFS를 통해 모든 상어의 경로를 구하기
        dfs(sx, sy, sd, eat);

        System.out.println(answer);
    }

    public static void dfs(int sx, int sy, int sd, int eat) {
        // 이전에 먹었던 물고기 번호 합 max를 비교해 큰 값 저장
        answer = Math.max(answer, eat);

        // map 배열 깊은복사 - 기존 map 유지를 위해 복사
        int[][] tempMap = new int[map.length][map.length];
        for(int i=0; i<map.length; i++)
            System.arraycopy(map[i], 0, tempMap[i], 0, map.length);

        // fish 배열 깊은복사
        Fish[] tempFish = new Fish[fish.length];
        for(int i=1; i<=16; i++)
            tempFish[i] = new Fish(fish[i].num, fish[i].x, fish[i].y, fish[i].dir, fish[i].alive);

        // 물고기 이동
        moveFish();

        // 상어 이동
        for(int i=1; i<4; i++) {
            int nx = sx + dx[sd]*i;
            int ny = sy + dy[sd]*i;

            // 경계 안 and 물고기가 있음
            if(nx>=0 && nx<4 && ny>=0 && ny<4 && map[nx][ny]!=0) {
                int eatFish = map[nx][ny];
                int nextDir = fish[eatFish].dir;

                // 물고기 먹음
                map[sx][sy] = 0;  // 현재 상어 위치 null
                map[nx][ny] = -1; // 상어 이동
                fish[eatFish].alive = 0;

                // 이동한 새로운 칸에서부터 다시 DFS 수행
                dfs(nx, ny, nextDir, eat+eatFish);

                // 다음 DFS를 위해 상어가 먹었던 물고기 살림
                fish[eatFish].alive = 1;

                // 위치 원래대로..
                map[nx][ny] = eatFish;
                map[sx][sy] = -1;
            }
        }

        // 맵 상태 and 물고기 정보 되돌리기
        for(int j=0; j<map.length; j++)
            System.arraycopy(tempMap[j], 0, map[j], 0, map.length);

        for(int j=1; j<=16; j++)
            fish[j] = new Fish(tempFish[j].num, tempFish[j].x, tempFish[j].y, tempFish[j].dir, tempFish[j].alive);
    }

    // 물고기 이동
    public static void moveFish() {
        for(int i=1; i<=16; i++) {
            if(fish[i].alive == 0)
                continue;

            int count = 0;
            int dir = fish[i].dir;  // 현재 i번째 물고기의 방향
            int nx=0, ny=0;         // 물고기가 이동할 칸의 x,y값

            while(count < 8) {
                // 방향 update
                dir %= 8;
                fish[i].dir = dir;

                nx = fish[i].x + dx[dir];
                ny = fish[i].y + dy[dir];

                // 이동할 위치에 상어가 없고 경계 안이면 이동 가능
                if(nx>=0 && nx<4 && ny>=0 && ny<4 && map[nx][ny] != -1) {
                    if(map[nx][ny] == 0) {
                        // 이동할 위치가 빈칸이면
                        map[fish[i].x][fish[i].y] = 0; // 기존 위치 빈칸으로
                        fish[i].x = nx;
                        fish[i].y = ny;
                        map[nx][ny] = i;
                    } else {
                        // 이동할 위치에 다른 물고기가 있다면
                        // 바꿀 물고기 위치 변경
                        int change = fish[map[nx][ny]].num;
                        fish[change].x = fish[i].x;
                        fish[change].y = fish[i].y;
                        map[fish[change].x][fish[change].y] = change;

                        // 현재 물고기 위치 변경
                        fish[i].x = nx;
                        fish[i].y = ny;
                        map[nx][ny] = i;
                    }
                    break;
                }

                dir++;
                count++;
            }
        }
    }
}
