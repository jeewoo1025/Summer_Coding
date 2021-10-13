package basic;

import java.io.*;
import java.util.*;

public class swea_16236 {
    static class Fish implements Comparable<Fish> {
        int row,col,time;
        public Fish(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        // 오름차순 : 자신 - 타원소
        // 내림차순 : 타원소 - 자신

        @Override
        public int compareTo(Fish o) {   // (기준값).compareTo(비교대상)
            if(o.time == this.time) {
                if(o.row == this.row)
                    return this.col - o.col;
                else
                    return this.row - o.row;
            }
            return this.time - o.time;
        }
    }

    static int N, totalFish=0, eatenFish = 0;
    static boolean[][] visited;
    static int[][] map;

    // 상어
    static Fish shark;
    static int sharkSize = 2;

    static ArrayList<Fish> feedList = new ArrayList<>();
    static Queue<Fish> queue = new LinkedList<>();
    static int answer;

    // 상, 하, 좌, 우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 9) {  // 상어 위치
                    shark = new Fish(i, j, 0);
                    map[i][j] = 0;
                }
                else if(map[i][j] >= 1)
                    totalFish++;
            }
        }

        queue.add(shark);
        visited[shark.row][shark.col] = true;

        // debug
        System.out.println("현재 상어의 크기 : "+sharkSize);

        while(true) {
            // debug
            System.out.println("\n현재 상어 위치 : "+shark.row+", "+shark.col);
            System.out.println("현재 시간 : "+answer);

            // 현재 위치에서 먹을 수 있는 물고기 탐색 (BFS)
            bfs();

            // debug
            printFeedList();

            // 먹을 수 있는 물고기 중 가장 우선순위가 높은 거 먹기
            if(!feedList.isEmpty()) {
                eat();

                // 먹은 거 초기화
                feedList.clear();

                // 식사 끝났으면 방문배열 초기화
                queue.clear();
                visited = new boolean[N][N];

                // 다시 이동하는 큐에다가 현재 상어 추가
                queue.add(shark);
                visited[shark.row][shark.col] = true;
            } else {
                // 더 이상 먹을 수 있는 물고기가 없다면 탈출하기
                break;
            }
        }
        System.out.println(answer);
    }

    public static void printFeedList() {
        StringBuilder sb = new StringBuilder("============ 어망 ============\n");
        for(Fish f:feedList) {
            sb.append(String.format("물고기 크기: %d, 위치: %d, %d \n", map[f.row][f.col], f.row, f.col));
        }
        sb.append("==========================");
        System.out.println(sb.toString());
    }

    public static void bfs() {
        while(!queue.isEmpty()) {
            Fish now = queue.poll();
            int time = now.time;

            for(int i=0; i<4; i++) {
                int nr = now.row + dr[i];
                int nc = now.col + dc[i];

                // 갈 수 있는가?
                if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc])
                    continue;

                Fish fish = new Fish(nr, nc, time+1);

                // 식사 가능
                if(map[nr][nc]>0 && map[nr][nc]<sharkSize) {
                    queue.add(fish);
                    visited[nr][nc] = true;
                    feedList.add(fish);
                }

                // 이동 가능 : 사이즈 동일 or 0
                if(map[nr][nc] == 0 || map[nr][nc] == sharkSize) {
                    queue.add(fish);
                    visited[nr][nc] = true;
                }
            }
        }
    }

    public static void eat() {
        // 먹을 물고기 리스트 중 맨위 좌측 먹기
        Collections.sort(feedList);
        Fish now = feedList.get(0);

        // debug
        System.out.println(now.row + ", " + now.col + "을 먹음, 크기 : " +map[now.row][now.col] + " , 걸린 시간 : " + now.time);

        // 물고기 먹기
        eatenFish++;
        if(eatenFish == sharkSize) {
            sharkSize++;
            eatenFish = 0;

            // debug
            System.out.println("현재 상어의 크기 : "+sharkSize);
        }
        map[now.row][now.col] = 0;

        // 상어 update
        shark.row = now.row;
        shark.col = now.col;

        // 시간 update
        answer += now.time;
    }
}
