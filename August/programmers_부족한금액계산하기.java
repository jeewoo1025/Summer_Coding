// 프로그래머스 부족한 금액 계산하기
// https://programmers.co.kr/learn/courses/30/lessons/82612

class Solution {
    public long solution(int price, int money, int count) {
        // 1 ~ N까지의 총합 = N(N+1)/2 가우스 공식
        long total = count*(count+1)/2;

        // 놀이기구 이용료 * 1 ~ N까지의 총합 = 총 이용료
        long answer = price*total;

        // 금액이 부족하지 않으면 0 리턴
        if(money >= answer)
            return 0;

        return (answer-money);
    }
}