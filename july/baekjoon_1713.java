import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// 백준 후보추천하기 1713번
public class Main {
	static int N, K;
	static int[] inputs;
	static Person[] people;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("C:\\Users\\jeewo\\eclipse-workspace\\Weclom\\input.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		inputs = new int[K];
		people = new Person[101];

		List<Person> list = new ArrayList<Person>();
		for(int i=0; i<K; i++) {
			int num = sc.nextInt();

			if(people[num] == null) {
				people[num] = new Person(num, 0, 0, false);
			}

			if(people[num].isIn == true) {
				people[num].count += 1;
			} else {
				// 사진 틀이 꽉 차있는 경우
				if(list.size() == N) {
					Collections.sort(list);
					Person p = list.remove(0);
					p.isIn = false;
				}

				people[num].count = 1;
				people[num].isIn = true;
				people[num].timeStamp = i; // 호출된 순서
				list.add(people[num]);
			}
		}

		Collections.sort(list, new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				return p1.num - p2.num;
			}
		});

		for(Person p: list)
			System.out.print(p.num + " ");
	}
}

class Person implements Comparable<Person> {
	int num;
	int count;
	int timeStamp;
	boolean isIn;

	public Person(int num, int count, int timeStamp, boolean isIn) {
		super();
		this.num = num;
		this.count = count;
		this.timeStamp = timeStamp;
		this.isIn = isIn;
	}

	@Override
	public String toString() {
		return "Person [num=" + num + ", count=" + count + ", timeStamp=" + timeStamp + ", isIn=" + isIn + "]";
	}

	public int compareTo(Person person) {
		// -1 : 바꾸지 않음 (오름차순)
		int result = count - person.count;
		if(result == 0) { // 0 : 바꾸지 않음
			return timeStamp - person.timeStamp;
		} else {
			return result;
		}
	}
}
