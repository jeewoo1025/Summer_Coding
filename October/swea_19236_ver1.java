package basic;

import java.io.*;
import java.util.*;

public class swea_19236 {
    static class Fish implements Comparable<Fish>, Cloneable {
        int dir,num;  // 방향, 번호
        int x,y;      // 행, 열

        public Fish(int x, int y, int dir, int num) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.num = num;
        }

        // 자기자신 - 남 --> 오름차순
        // 남 - 자기자신 --> 내림차순
        @Override
        public int compareTo(Fish o) {
            return o.num - this.num;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "dir=" + directions[dir] +
                    ", num=" + num +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

                // null, ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {0,-1,-1,0,1,1,1, 0,-1};
    static int[] dy = {0,0,-1,-1,-1,0,1,1,1};
    static char[] directions = {'z', '↑', '↖', '←', '↙', '↓', '↘', '→', '↗'};

    static Fish[][] board = new Fish[4][4];
    static Fish shark;
    static Fish[] fishList = new Fish[16+1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 물고기 입력
        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                board[i][j] = new Fish(i,j,dir,num);
                fishList[num] = new Fish(i,j,dir,num);
            }
        }

        // 상어가 이동할 수 없을때까지!
        while(true) {
            // 상어 이동
            boolean flag = moveShark();
            if(!flag)
                break;

            // 물고기 이동
            moveFishes();
        }

        System.out.println(shark.num);
   }

   public static boolean moveShark() {
        if(shark == null) {
            // 0,0 위치 물고기 먹기
            Fish fish = board[0][0];
            shark = new Fish(0,0,fish.dir,fish.num);

            // 0,0 위치 갱신
            board[0][0] = null;
            fishList[fish.num] = null;
            return true;
        }

        // 상어 이동하기
       // 상어가 가리키는 방향으로 모든 물고기 리스트 얻기
        List<Fish> tmp = new ArrayList<>();
        int x = shark.x + dx[shark.dir];
        int y = shark.y + dy[shark.dir];
        while(x>=0 && x<4 && y>=0 && y<4) {
            // 물고기 있음 물고기 추가
            if(board[x][y] != null)
                tmp.add(board[x][y]);

            x += dx[shark.dir];
            y += dy[shark.dir];
        }

        // 먹을 물고기가 없다!
        if(tmp.size() <= 0)
            return false;

        // 번호 내침차순으로 정렬
        Collections.sort(tmp);
        Fish obj = tmp.get(0);

        // 해당 물고기 먹기
        shark.x = obj.x;
        shark.y = obj.y;
        shark.dir = obj.dir;
        shark.num += obj.num;

        // 물고기 위치 갱신
        fishList[obj.num] = null;
        board[obj.x][obj.y] = null;

        return true;
   }

   public static void moveFishes() {
        // 1번 ~ 끝번호까지 이동하기
        for(int i=1; i<fishList.length; i++) {
            if(fishList[i] == null)
                continue;

            // 현재 물고기 가져오기
            Fish now = fishList[i];
            int dir = now.dir;
            //System.out.println(i+"번째 물고기 "+now);

            // 현재방향을 시작으로 8방향 탐색
            for(int j=0; j<8; j++) {
                //System.out.println("바꿀방향 : "+directions[dir]);
                // 현재 방향으로 갈 위치 가져오기
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];
                //System.out.println("nx :"+nx+" ny:"+ny+"  shark:"+shark.x+","+shark.y);

                // 이동가능 한가?
                if(nx>=0 && nx<4 && ny>=0 && ny<4 && board[nx][ny]!=null) {
                    if(!(shark.x == nx && shark.y == ny)) {  // 이동하기
                        // 방향 갱신하기
                        now.dir = dir;

                        int objNum = board[nx][ny].num;
                        Fish next = board[nx][ny];

                        // 방향, 번호 바꾸기
                        board[now.x][now.y].dir = next.dir;
                        board[now.x][now.y].num = next.num;
                        next.dir = now.dir;
                        next.num = now.num;

                        // fishList[i]의 위치 갱신
                        fishList[objNum].x = now.x;
                        fishList[objNum].y = now.y;
                        fishList[now.num].x = nx;
                        fishList[now.num].y = ny;
                        break;
                    }
                }

                // 방향 회전
                dir = rotateDir(dir);
            }
        }
   }

   // 45도 회전하기
   public static int rotateDir(int dir) {
        if(++dir>8)
            dir = 1;
        return dir;
   }

   public static void printBoard() {
       // debug
       StringBuilder sb = new StringBuilder();
       for(int i=0; i<4; i++) {
           for(int j=0; j<4; j++) {
               if(board[i][j] != null)
                   sb.append(board[i][j].num+ "("+directions[board[i][j].dir]+") ");
               else
                   sb.append("null ");
           }
           sb.append("\n");
       }
       System.out.println(sb.toString());
   }
}
