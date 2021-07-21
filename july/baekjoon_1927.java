// 백준 1927번 최소합

package day2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 백준 1927번 최소힙
public class num1_answer {
	static int N;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("C:\\Users\\jeewo\\eclipse-workspace\\Weclom\\input.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		MinHeap nh = new MinHeap();

		for(int i=0; i<N; i++) {
			int input = sc.nextInt();
			if(input == 0)
				System.out.println(nh.delete());
			else
				nh.insert(input);
		}
	}

}

class MinHeap {
	List<Integer> heap;

	public MinHeap() {
		heap = new ArrayList<Integer>();
		heap.add(0);
	}

	public void insert(int val) {
		heap.add(val);

		int current = heap.size()-1;
		int parent = current/2;
		while(true) {
			if(parent==0 || heap.get(parent) <= heap.get(current))
				break;

			int temp = heap.get(parent);
			heap.set(parent, heap.get(current));
			heap.set(current, temp);

			current = parent;
			parent = current/2;
		}
	}

	public int delete() {
		if(heap.size() <= 1)
			return 0;

		int top = heap.get(1);
		heap.set(1, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);

		int currentPos = 1;
		while(true) {
			int leftPos = currentPos*2;
			int rightPos = currentPos*2+1;

			// 자식 유무
			if(leftPos>=heap.size()) {  // leaf
				break;
			}

			int minVal = heap.get(leftPos);
			int minPos = leftPos;

			if(rightPos < heap.size() && minVal>heap.get(rightPos)) {
				minVal = heap.get(rightPos);
				minPos = rightPos;
			}

			if(heap.get(currentPos) > minVal) {
				int tmp = heap.get(currentPos);
				heap.set(currentPos, heap.get(minPos));
				heap.set(minPos, tmp);

				currentPos = minPos;
			} else
				break;
		}

		return top;
	}
}
