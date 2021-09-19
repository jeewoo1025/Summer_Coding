import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// �ھ� ��ġ ���� ����
class Core {
	int x,y;  // ��, ��
	
	public Core(int y, int x) {
		this.y = y;
		this.x = x;
	}	
}

public class swea_1767 {
	// N : �� ũ��, cell : �ھ�� ������ ���� ��, minWireLength : ���� �ּұ���, maxCore : �ھ� �ִ밪
	static int N, cell[][], minWireLength, maxCore;
	static int dx[] = {0,0,-1,1}; // �� �� �� ��
	static int dy[] = {-1,1,0,0};
	static List<Core> coreList; // �ھ� ��ġ ���� ����Ʈ
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			
			cell = new int[N][N];
			coreList = new ArrayList<>();
			
			// �Է�
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int in = Integer.parseInt(st.nextToken());
					if(in==1) {
						cell[i][j] = in;
						
						// �����ڸ��� �ִ� �ھ��� ����Ʈ�� ����X
						if(i==0 || j==0 || i==N-1 || j==N-1)
							continue;
						coreList.add(new Core(i,j));  // ��, ��
					}
					
				}
			}
			
			minWireLength = Integer.MAX_VALUE;
			maxCore = Integer.MIN_VALUE;
			
			System.out.println("���� == maxCore : "+maxCore+" , ������������ = "+minWireLength);
			startConnect(0,0,0);
			
			sb.append("#"+t+" "+minWireLength+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	/** 
	 * ���� ���� DFS 
	 * �ش� �ھ� ��ġ���� ��� Ž���� �Ѵ�.
	 * �� �������� ����ؼ� ���ư��� ��, ������ �����
	 * ���ع��� ���� ������ �Ǵ�. ������ ��ġ�Ѵ�.
	 * ���� ���߿� �ھ ������ �����ٸ� ������ �ٲ㼭 �ٽ� ī�����Ѵ�.
	 * ī������ �Ǿ��ٸ� �ش� ������ �����ؼ� ���� DFS
	 * ī������ ���ٸ� �ε����� �÷��� DFS
	 * */
	public static void startConnect(int idx, int coreCnt, int wireCnt) {
		if(idx == coreList.size()) {  
			if(maxCore < coreCnt) { // �ִ��� ���� core�� ����
				maxCore = coreCnt;
				minWireLength = wireCnt;
				
				System.out.println("maxCore : "+coreCnt+" , ������������ = "+wireCnt);
			} else if(maxCore == coreCnt) { // ���������� ���� �ּҰ� �Ǵ� ��
				minWireLength = Math.min(wireCnt, minWireLength);
				
				System.out.println("maxCore : "+coreCnt+" , ������������ = "+wireCnt);
			}
			return;
		}
		
		// �ε��� ��ġ�� �ھ��� ��ǥ
		int x = coreList.get(idx).x;
		int y = coreList.get(idx).y;
		
		// �� �� �� �� Ž��
		for(int dir=0; dir<4; dir++) {
			int count=0, nx=x, ny=y;
			
			while(true) {
				nx += dx[dir];
				ny += dy[dir];
				
				// ������ ����� is �ٸ��ھ ������ ������ ����
				if(ny<0 || ny>=N || nx<0 || nx>=N) {
					break;
				}
				
				// ���� �濡 �ٸ� �ھ� Ȥ�� ���� ���� -> �ٸ� ��������
				if(cell[ny][nx] == 1) {
					count = 0;
					break;
				}
				
				// ��� ���ص� ���ٸ� +1
				count++;
			}

			// count ������ŭ 1�� ä����
			int fill_x = x;
			int fill_y = y;
			
			for(int i=0; i<count; i++) {
				fill_x += dx[dir];
				fill_y += dy[dir];
				cell[fill_y][fill_x] = 1;
			}
			
			// � ��쿡�� ������ ��ġ�� �� ����
			// ������ �� �ص� ������ �ȵǴ� �ھ ������ �� �ִ�
			// �ε����� �ϳ��� ���� and �ھ�&������ ���� �״�� ���� Ž��
			if(count==0)
				startConnect(idx+1, coreCnt, wireCnt);
			else {
				// ī��Ʈ�� �Ǿ��ٴ� �Ҹ��� ī��Ʈ�� ���ڸ�ŭ ������ ��
				// ���� �ε���&�ھ� ���� and ������ ī��Ʈ ����ŭ ���ؼ� ���� Ž��
				startConnect(idx+1, coreCnt+1, wireCnt+count);
				
				// �������� �ٽ� 0���� �ǵ���
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
