// 백준 1202번 보석도둑
package day2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1202번 보석도둑
public class num2_answer {
	static int N,K;
	static Jewerly jeweleris[];
	static int bags[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());  // 보석
		K = Integer.parseInt(st.nextToken());  // 가방

		jeweleris = new Jewerly[N];
		bags = new int[K];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			jeweleris[i] =
					new Jewerly(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for(int i=0; i<K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}

		// 가방 오름 차순 정렬
		Arrays.sort(bags);

		// 보석 무게순 정렬
		Arrays.sort(jeweleris, Comparator.comparingInt(Jewerly::getWeight));

		// 보석 높은 값 기준 힙 (내림차순)
		PriorityQueue<Jewerly> pq = new PriorityQueue<>(Comparator.comparingInt(Jewerly::getPrice).reversed());

		int jIndex=0;
		long result = 0;

		// 남은 가방 중 제일 작은 가방 선택 (정렬)
		for(int i=0; i<bags.length; i++) {
			// 선택된 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택
			while(jIndex<N && jeweleris[jIndex].weight<=bags[i]) {
				pq.add(jeweleris[jIndex++]);
			}

			if(!pq.isEmpty()) {
				result += pq.poll().price;
			}
		}

		System.out.println(result);
	}
}

class Jewerly {
	int weight;
	int price;

	public int getWeight() {return weight;}
	public int getPrice() {return price;}

	public Jewerly(int w, int p) {
		this.weight = w;
		this.price = p;
	}

	public String toString() {
		return "("+weight+", "+price+")";
	}
}