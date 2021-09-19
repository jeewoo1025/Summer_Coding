import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 코어 위치 정보 저장
class Core {
	int x,y;  // 열, 행
	
	public Core(int y, int x) {
		this.y = y;
		this.x = x;
	}	
}

public class swea_1767 {
	// N : 셀 크기, cell : 코어와 전선을 답을 맵, minWireLength : 전선 최소길이, maxCore : 코어 최대값
	static int N, cell[][], minWireLength, maxCore;
	static int dx[] = {0,0,-1,1}; // 상 하 좌 우
	static int dy[] = {-1,1,0,0};
	static List<Core> coreList; // 코어 위치 담을 리스트
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			
			cell = new int[N][N];
			coreList = new ArrayList<>();
			
			// 입력
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int in = Integer.parseInt(st.nextToken());
					if(in==1) {
						cell[i][j] = in;
						
						// 가장자리에 있는 코어라면 리스트에 저장X
						if(i==0 || j==0 || i==N-1 || j==N-1)
							continue;
						coreList.add(new Core(i,j));  // 행, 열
					}
					
				}
			}
			
			minWireLength = Integer.MAX_VALUE;
			maxCore = Integer.MIN_VALUE;
			
			System.out.println("시작 == maxCore : "+maxCore+" , 전선길이의합 = "+minWireLength);
			startConnect(0,0,0);
			
			sb.append("#"+t+" "+minWireLength+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	/** 
	 * 전선 연결 DFS 
	 * 해당 코어 위치에서 사방 탐색을 한다.
	 * 한 방향으로 계속해서 나아갔을 때, 범위를 벗어나면
	 * 방해물이 없는 것으로 판단. 전선을 설치한다.
	 * 가는 도중에 코어나 전선을 만난다면 방향을 바꿔서 다시 카운팅한다.
	 * 카운팅이 되었다면 해당 정보를 누적해서 다음 DFS
	 * 카운팅이 없다면 인덱스만 늘려서 DFS
	 * */
	public static void startConnect(int idx, int coreCnt, int wireCnt) {
		if(idx == coreList.size()) {  
			if(maxCore < coreCnt) { // 최대한 많은 core에 연결
				maxCore = coreCnt;
				minWireLength = wireCnt;
				
				System.out.println("maxCore : "+coreCnt+" , 전선길이의합 = "+wireCnt);
			} else if(maxCore == coreCnt) { // 전선길이의 합이 최소가 되는 값
				minWireLength = Math.min(wireCnt, minWireLength);
				
				System.out.println("maxCore : "+coreCnt+" , 전선길이의합 = "+wireCnt);
			}
			return;
		}
		
		// 인덱스 위치의 코어의 좌표
		int x = coreList.get(idx).x;
		int y = coreList.get(idx).y;
		
		// 상 하 좌 우 탐색
		for(int dir=0; dir<4; dir++) {
			int count=0, nx=x, ny=y;
			
			while(true) {
				nx += dx[dir];
				ny += dy[dir];
				
				// 범위를 벗어났다 is 다른코어나 전선을 만나지 않음
				if(ny<0 || ny>=N || nx<0 || nx>=N) {
					break;
				}
				
				// 가는 길에 다른 코어 혹은 전선 존재 -> 다른 방향으로
				if(cell[ny][nx] == 1) {
					count = 0;
					break;
				}
				
				// 어떠한 방해도 없다면 +1
				count++;
			}

			// count 갯수만큼 1로 채워줌
			int fill_x = x;
			int fill_y = y;
			
			for(int i=0; i<count; i++) {
				fill_x += dx[dir];
				fill_y += dy[dir];
				cell[fill_y][fill_x] = 1;
			}
			
			// 어떤 경우에도 전선을 설치할 수 없다
			// 연결을 다 해도 연결이 안되는 코어가 존재할 수 있다
			// 인덱스를 하나만 증가 and 코어&전선의 수는 그대로 다음 탐색
			if(count==0)
				startConnect(idx+1, coreCnt, wireCnt);
			else {
				// 카운트가 되었다는 소리는 카운트의 숫자만큼 전선이 깔림
				// 다음 인덱스&코어 증가 and 전선을 카운트 수만큼 더해서 다음 탐색
				startConnect(idx+1, coreCnt+1, wireCnt+count);
				
				// 원본맵을 다시 0으로 되돌림
				fill_x = x;
				fill_y = y;
				
				for(int i=0; i<count; i++) {
					fill_x += dx[dir];
					fill_y += dy[dir];
					cell[fill_y][fill_x] = 0;
				}
			}
		}
	}
}
