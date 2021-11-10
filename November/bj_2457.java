import java.io.*;
import java.util.*;

public class bj_2457 {
    static class Date implements Comparable<Date> {
        int month, day;
        public Date(int month, int day) {
            this.month = month;
            this.day = day;
        }

        @Override
        public int compareTo(Date o) {  // 오름 차순
            if(this.month == o.month) { // 달이 같다면 날짜 비교
                return this.day - o.day;
            }
            return this.month - o.month;
        }

        public void update(int month, int day) {
            this.month = month;
            this.day = day;
        }

        @Override
        public String toString() {
            return month+"/"+day;
        }
    }

    static class Flower implements Comparable<Flower> {
        Date start, end; // 피는날, 지는날

        public Flower(int startM, int startD, int endM, int endD) {
            start = new Date(startM, startD);
            end = new Date(endM, endD);
        }

        @Override
        public int compareTo(Flower o) {  // 꽃 지는날 기준으로 내림차순
            return o.end.compareTo(this.end);
        }

        @Override
        public String toString() {
            return "Flower{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    static int N, count=0;
    static List<Flower> flowerList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int m1,d1,m2,d2;
            m1 = Integer.parseInt(st.nextToken());
            d1 = Integer.parseInt(st.nextToken());
            m2 = Integer.parseInt(st.nextToken());
            d2 = Integer.parseInt(st.nextToken());

            flowerList.add(new Flower(m1,d1,m2,d2));
        }

        Date end, cur;
        end = new Date(11, 30);
        cur = new Date(3, 1);

        // 1-1. 날짜 순으로 내림차순 정렬
        Collections.sort(flowerList);

        while(!flowerList.isEmpty()) {
            // 1. 현재 날짜 기준으로 최적의 결정 pick
            // 1-2. List에서 기한 내에 거르기
            int i;
            for(i=0; i<flowerList.size(); i++) {
                Flower f = flowerList.get(i);

                // 현재날짜 >= 꽃의 피는날
                if(cur.compareTo(f.start)>=0 && cur.compareTo(f.end)<=0) {
                    break;
                }
            }

            // 원하는 꽃이 없을때
            if(i>=flowerList.size()) {
                count = 0;
                break;
            }

            Flower obj = flowerList.remove(i);
            // System.out.println("사용한 꽃 - "+obj);

            // 2. 꽃 고르기
            count++;
            cur.update(obj.end.month, obj.end.day);
            // System.out.println("현재날짜 : "+cur);

            // 3. 날짜 check
            if(cur.compareTo(end)>0) // 현재 날짜 > 11/30
                break;
        }

        // 원하는 꽃이 없을 때
        if(cur.compareTo(end)<=0)  // 현재날짜 < 마지막날
            count = 0;
        System.out.println(count);
    }
}
