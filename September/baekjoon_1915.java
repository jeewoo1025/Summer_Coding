import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1915 {
	static int N,M;
	static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];

		boolean isAllZero = false;
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				board[i][j] = s.charAt(j)-'0';
				if(board[i][j] > 0)
					isAllZero = true;
			}
		}

		// 정사각형 최대길이 초기화
		int max_length = 1;
		if(!isAllZero) {
			System.out.println(0);
			return;
		}

		// (행,열) = (1,1) ~ (N-1, M-1)까지 조회
		for(int i=1; i<N; i++) {
			for(int j=1; j<M; j++) {
				// 현재 값이 1인가?
				if(board[i][j] <= 0)
					continue;

				// ← 왼 ↖ 왼대각선 ↑ 위 가 모두 1이면 정사각형
				if(board[i][j-1]>0 && board[i-1][j-1]>0 && board[i-1][j]>0) {
					// 3개 중 최소값+1로 dp 값 증가
					int min_val = Math.min(board[i][j-1], board[i-1][j-1]);
					min_val = Math.min(min_val, board[i-1][j]);
					board[i][j] = min_val + 1;

					// 이때, 값>max_length이면 최대길이 update
					if(board[i][j] > max_length)
						max_length = board[i][j];
				}
			}
		}

		// 정사각형 넓이 출력
		System.out.println(max_length*max_length);
	}
}
