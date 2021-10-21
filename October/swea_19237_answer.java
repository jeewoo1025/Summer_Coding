package basic;

import java.io.*;
import java.util.*;

public class swea_19237_answer {
    static class Shark {
        int id, x, y, dir;
        int[][] priority = new int[5][5];  // priority[현재방향][우선순위]

        public Shark(int id, int x, int y) {
            this.x = x;
            this.y = y;
            this.id = id;
        }

        int findNextDir(Set<Integer> candidates) {
            for(int i=1; i<=4; i++) {
                if(candidates.contains(priority[dir][i]))
                    return priority[dir][i];
            }
            return 0;
        }
    }

    static int N,M,k;
    static int[][] arr = new int[21][21];  // 상어의 위치
    static int[][] smellOwner = new int[21][21]; // 냄새의 주인
    static int[][] leftTime = new int[21][21]; // 냄새의 남은 시간
    static Map<Integer, Shark> sharks = new HashMap<>();
    static int time = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] > 0) {  // 상어임
                    Shark s = new Shark(arr[i][j], i, j);
                    sharks.put(s.id, s);

                    smellOwner[i][j] = s.id;
                    leftTime[i][j] = k;
                }
            }
        }

        // direction of sharks
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            Shark s = sharks.get(i);
            s.dir = Integer.parseInt(st.nextToken());
        }

        // priority of sharks
        for (int i = 1; i <= M; i++) {
            Shark s = sharks.get(i);

            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());

                for (int z = 0; z < 4; z++) {
                    s.priority[j + 1][z + 1] = Integer.parseInt(st.nextToken());
                }
            }
        }

        while(time++ < 1000) {
            moveShark();
            decreaseSmellTime();
            createSmell();

            if(sharks.size() == 1) {
                System.out.println(time);
                return;
            }
        }
        System.out.println(-1);
    }

    // 상어 이동 후 겹친 후 쫓아내기
    public static void moveShark() {
        // 상, 하, 좌, 우
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        Queue<Integer> willRemove = new LinkedList<>();

        for(Integer id:sharks.keySet()) {
            Set<Integer> noSmellSet = new HashSet<>();
            Set<Integer> mySmellSet = new HashSet<>();
            Shark shark = sharks.get(id);

            // 다음 방향 계산
            for(int i=0; i<4; i++) {
                int nx = shark.x + dx[i];
                int ny = shark.y + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=N)
                    continue;

                if(smellOwner[nx][ny] == 0)
                    noSmellSet.add(i+1);
                else if(smellOwner[nx][ny] == shark.id)
                    mySmellSet.add(i+1);
            }

            // 냄새 없는 곳부터 스캔
            int nextDir = shark.findNextDir(noSmellSet);
            if(nextDir==0)
                nextDir = shark.findNextDir(mySmellSet);

            // 이동
            arr[shark.x][shark.y] = 0;
            shark.x += dx[nextDir-1];
            shark.y += dy[nextDir-1];

            // 이동할 위치에 상어 있음 경쟁을 통해 작은 번호의 상어 생존
            if(arr[shark.x][shark.y] == 0 || shark.id < arr[shark.x][shark.y]) {
                arr[shark.x][shark.y] = shark.id;
                shark.dir = nextDir;
            } else
                willRemove.add(shark.id);
        }

        // 상어 한꺼번에 제거
        while(!willRemove.isEmpty())
            sharks.remove(willRemove.poll());
    }

    // 전체 냄새 시간을 하나씩 감소 후 0이 되면 주인 정보 지움
    public static void decreaseSmellTime() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(leftTime[i][j] == 0)
                    continue;

                leftTime[i][j]--;
                if(leftTime[i][j]==0)
                    smellOwner[i][j] = 0;
            }
        }
    }

    // 상어가 이동한 곳에 새로운 냄새 생성
    public static void createSmell() {
        for(Integer id:sharks.keySet()) {
            Shark shark = sharks.get(id);
            smellOwner[shark.x][shark.y] = shark.id;
            leftTime[shark.x][shark.y] = k;
        }
    }
}
