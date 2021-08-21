import java.util.ArrayList;
import java.util.List;

public class Num2 {
	static int MAX_DOT=27;

	public static void main(String[] args) {
		String[] code = {
				"a=3", "..a=4", "..b=3", "..print a", ".......a=6", ".......print a", ".......print b", "..print a", "....a=7", "....print a", "print a", "print b", "a=4", "print a", "...print a"
		};

		String[] answer = solution(code);
		System.out.println("--- 답 ---");
		for(String s : answer)
			System.out.println(s);
	}

	public static String[] solution(String[] code) {
		List<String> answer = new ArrayList<String>();
		int curr_pos, before_pos=-1;  // 현재 위치, 이전 위치

		// 블록들 초기화
		ArrayList[] blocks = new ArrayList[MAX_DOT+1];
		for(int i=0; i<=MAX_DOT; i++) {
			blocks[i] = new ArrayList<Variable>();
		}

		for(String order:code) {
			//System.out.print("order : "+order);

			// 1. 블록 확인 - "." 갯수 세기
			curr_pos = countDot(order);
			//System.out.println("   curr pos : "+curr_pos);

			// "." 치환
			order = order.replace(".", "");

			// 1-3. 첫번째 원소 X and 블록이 제거됨
			if(before_pos >= 0 && before_pos > curr_pos) {
				for(int p=curr_pos+1; p<=before_pos; p++)
					blocks[p].clear();
			}

			// 2. ~ curr_pos 블록까지 명령어 수행
			if(order.startsWith("print")) {
				String var = order.replace("print ", "");
				int num = 0;

				for(int i=curr_pos; i>=0; i--) {
					for(int j=blocks[i].size()-1; j>=0; j--) {
						// 3. 변수가 해당 블록에 존재?
						Variable tmp = (Variable)blocks[i].get(j);

						if(var.equals(tmp.name)) {
							//System.out.println("print 명령어 : 변수가 존재함" + tmp.name);
							num = tmp.num;
							break;
						}
					}

					if(num > 0)
						break;
				}

				if(num <= 0) {  	// 3-1. 존재 X --> error
					answer.add("error");
				} else {  // 3-2. 변수가 블록에 존재 O --> 가장 나중에 생성된 변수 출력
					answer.add(var+"="+num);
				}
			} else {  // 4. 변수명=숫자
				String[] tmp = order.split("=");
				blocks[curr_pos].add(new Variable(tmp[0], Integer.parseInt(tmp[1])));
			}

			before_pos = curr_pos;
		}

		return answer.toArray(new String[0]);
	}

	public static int countDot(String val) {
		int total = 0;
		for(int i=0; i < val.length(); i++) {
			if(val.charAt(i) == '.')
				total += 1;
		}

		return total;
	}
}

class Variable {
	String name;
	int num;

	public Variable(String name, int num) {
		this.name = name;
		this.num = num;
	}
}
