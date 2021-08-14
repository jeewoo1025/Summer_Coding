class Solution {
    public String solution(int[][] scores) {
        StringBuilder sb = new StringBuilder();

        // N : 학생 수
        int N = scores.length;

        // 1. i번 학생마다 평균 구하기 (자기자신 제외)
        for(int i=0; i<N; i++) {
            int max = -1, min = 101;
            int sum = 0;

            // 2. j번 돌면서 총합과 최대, 최소 구하기
            for(int j=0; j<N; j++) {
                if(i == j)
                    continue;

                sum += scores[j][i];
                max = Math.max(max, scores[j][i]);
                min = Math.min(min, scores[j][i]);
            }

            // 3. 자기자신 > max or 자기자신 < min 이라면 유일한 최대/최소
            if(scores[i][i] > max || scores[i][i] < min) {
                sb.append(calGrade(sum/(N-1)));
            } else {
                sum += scores[i][i];
                sb.append(calGrade(sum/N));
            }
        }

        return sb.toString();
    }

    // 학점 계산기
    public char calGrade(float val) {
        if(val >= 90)
            return 'A';
        else if(val >= 80)
            return 'B';
        else if(val >= 70)
            return 'C';
        else if(val >= 50)
            return 'D';
        return 'F';
    }
}
