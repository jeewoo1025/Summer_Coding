import java.util.HashMap;
import java.util.Map;

public class Num1 {
	public static void main(String[] args) {
		//int[][] passwords = {{101,1234},{102,54321},{201,202},{202,1}};
		int[][] passwords = {{101,9999},{102,1111}};
		String s = "201#9999#101#";

		solution(passwords, s);
	}

	public static int solution(int[][] passwords, String s) {
        int count = 0;

        // Map - Key : 호수, Value : 비밀번호
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<passwords.length; i++) {
			map.put(passwords[i][0], passwords[i][1]);
		}

        // 1. # 로 분할
        String[] words = s.split("#");
        int idx = 0;

        while(idx < words.length) {
            Integer word = Integer.parseInt(words[idx]);

            // 2. 호수가 있는가?
            // 2-1. 있는 호수이면 해당 호수의 비밀번호 확인
            if(map.containsKey(word)) {
                int pwd = map.get(word);

                // 3. 비밀번호가 일치하는가?
                // 3-1. 다음에 비밀번호가 존재하는가?
                idx += 1;
                if(idx >= words.length)
                    break;

                // 3-2 일치 --> 횟수+1
                if(pwd == Integer.parseInt(words[idx])) {
                    count += 1;
                }
            }

            idx += 1;
        }

        return count;
	}
}