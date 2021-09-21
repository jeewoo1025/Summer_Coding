package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class swea_1953 {
    static class Node {
        int r,c;
        public Node(int r, int c) { this.r = r; this.c = c; }
    }

    static int N, M, R, C, L;    // N: 세로, M : 가로, 맨홀 뚜겅이 위치한 장소 (R, C), 탈출 후 소요된 시간
    static int[][] tunnel; // 지하 터널
    static boolean[][] visited;   // 방문여부
    static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static Queue<Node> queue;
    static int totalCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            // 초기화
            totalCount = 1;
            queue = new LinkedList<>();
            tunnel = new int[N][M];
            visited = new boolean[N][M];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<M; j++) {
                    tunnel[i][j] = Integer.parseInt(st.nextToken());
                    visited[i][j] = false;
                }
            }

            bfs();
            sb.append(String.format("#%d %d\n", t, totalCount));
        }

        System.out.println(sb.toString());
    }

    public static void bfs() {
        int time = 0;

        queue.offer(new Node(R, C));
        visited[R][C] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            if (++time >= L)
                return;

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                int type = tunnel[cur.r][cur.c];

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dir[d][0];
                    int nc = cur.c + dir[d][1];

                    // 범위 밖
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;

                    // 방문 했거나 터널 존재 X
                    if (visited[nr][nc] || tunnel[nr][nc] == 0)
                        continue;

                /*  + : 1   | : 2   - : 3
                    ┕ : 4   ┎ : 5   ┑ : 6   ┚ : 7  */
                    int next = tunnel[nr][nc];
                    switch (d) {
                        case 0:  // 위쪽 체크할 때
                            // 현재 위치의 파이프가 위쪽으로 열려있고
                            if (type == 1 || type == 2 || type == 4 || type == 7) {
                                // 다음 위치의 파이프가 아래쪽으로 열려있으면 이동 가능
                                if (next == 1 || next == 2 || next == 5 || next == 6) {
                                    visited[nr][nc] = true;
                                    queue.offer(new Node(nr, nc));
                                    totalCount++;
                                }
                            }
                            break;
                        case 1:  // 아래쪽 체크할 때
                            // 현재 위치의 파이프가 아래쪽으로 열려있고
                            if (type == 1 || type == 2 || type == 5 || type == 6) {
                                // 다음 위치의 파이프가 위쪽으로 열려있으면 이동 가능
                                if (next == 1 || next == 2 || next == 4 || next == 7) {
                                    visited[nr][nc] = true;
                                    queue.offer(new Node(nr, nc));
                                    totalCount++;
                                }
                            }
                            break;
                        case 2: // 왼쪽 체크할 때
                            // 현재 위치의 파이프가 왼쪽으로 열려있고
                            if (type == 1 || type == 3 || type == 6 || type == 7) {
                                if (next == 1 || next == 3 || next == 4 || next == 5) {
                                    visited[nr][nc] = true;
                                    queue.offer(new Node(nr, nc));
                                    totalCount++;
                                }
                            }
                            // 다음 위치의 파이프가 오른쪽으로 열려있으면 이동가능
                            break;
                        case 3: // 오른쪽 체크할 때
                            // 현재 위치의 파이프가 오른쪽으로 열려있고
                            if (type == 1 || type == 3 || type == 4 || type == 5) {
                                // 다음 위치의 파이프가 왼쪽으로 열려있으면 이동가능
                                if (next == 1 || next == 3 || next == 6 || next == 7) {
                                    visited[nr][nc] = true;
                                    queue.offer(new Node(nr, nc));
                                    totalCount++;
                                }
                            }
                            break;
                    }
                }
            }
        }
    }
}
