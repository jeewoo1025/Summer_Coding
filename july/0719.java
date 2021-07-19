import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 탈출 - 3055번

public class example2 {
	static int R;
	static int C;
	static char[][] map; // 숲의 정보
	static boolean[][] visited; // 방문관리
	static int minCnt;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static Queue<Pos> moveQ = new LinkedList<Pos>();
	static Queue<Pos> waterQ = new LinkedList<Pos>();

	// 고슴도치 이동 가능?
	private static boolean isMove(int row, int col) {
		if(map[row][col] == '.' || map[row][col] == 'D') {
			return true;
		}
		return false;
	}

	// 범위 검사
	private static boolean isIn(int row, int col) {
		if(row<0 || row>=R || col<0 || col>=C) {
			return false;
		}
		return true;
	}

	// 물 퍼짐
	private static void spread() {
		int cnt = waterQ.size();

		while(cnt-- > 0) {
			Pos waterPos = waterQ.poll();
			int row = waterPos.row;
			int col = waterPos.col;

			for(int d=0; d<4; d++) {
				int nextRow = row + dy[d];
				int nextCol = col + dx[d];

				// 범위 검사 and 물이 찰 수 있음
				if(isIn(nextRow, nextCol) && map[nextRow][nextCol] == '.') {
					waterQ.offer(new Pos(nextRow, nextCol));
					map[nextRow][nextCol] = '*';
				}
			}
		}
	}

	private static void bfs(int move) {
		int cnt = moveQ.size();

		while(cnt-- > 0) {
			Pos pos = moveQ.poll();
			int row = pos.row;
			int col = pos.col;

			for(int d=0; d<4; d++) {
				int nextRow = row + dy[d];
				int nextCol = col + dx[d];

				// 범위 검사 and 이동 가능?
				if(isIn(nextRow, nextCol) && isMove(nextRow, nextCol)) {
					// 비버 발견
					if(map[nextRow][nextCol] == 'D') {
						minCnt = move;
						moveQ.clear();
						return;
					}

					// 방문 X
					if(!visited[nextRow][nextCol]) {
						moveQ.offer(new Pos(nextRow, nextCol));
						visited[nextRow][nextCol] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];
		minCnt = Integer.MAX_VALUE;
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				char c = str.charAt(j);
				map[i][j] = c;

				if(c=='S') {  // 고슴도치
					moveQ.offer(new Pos(i,j));
					visited[i][j] = true;
				} else if(c=='*') { // 물
					waterQ.offer(new Pos(i,j));
				}
			}
		}

		int move=0; // 이동 횟수
		while(!moveQ.isEmpty()) {
			spread(); // 물 퍼침
			bfs(++move);  // 고슴도치 이동
		}

		if(Integer.MAX_VALUE==minCnt)
			System.out.println("KAKTUS");
		else
			System.out.println(minCnt);
		br.close();
	}
}

class Pos {
	int row;
	int col;

	public Pos(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
