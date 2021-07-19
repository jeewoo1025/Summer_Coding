import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

// 백준 고스텍 - 3425번

public class ddd {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long MAX = 1000000000;
		String command;
		List<String> commands = new ArrayList<String>();

		while(true) {
			command = sc.nextLine();

			if(command.equals("QUIT"))
				break;
			if(command.equals("")) {
				commands.clear();
				continue;
			}

			if(command.equals("END")) {
				int n = sc.nextInt();

				for(int i=0; i<n; i++) {
					Stack<Long> stack = new Stack();

					long tmp = sc.nextInt();
					stack.push(tmp);

					boolean error = false;
					for(String s:commands) {
						String curCommand = s.substring(0,3);

						if(curCommand.equals("NUM")) {
							stack.push(Long.parseLong(s.substring(4)));
						} else if(curCommand.equals("POP")) {
							if(stack.empty()) {
								error = true;
								break;
							}
							stack.pop();
						} else if(curCommand.equals("INV")) {
							if(stack.empty()) {
								error = true;
								break;
							}
							long p = stack.pop();
							stack.push(-p);
						} else if(curCommand.equals("DUP")) {
							long p = stack.peek();
							stack.push(p);
						} else if(curCommand.equals("SWP")) {
							if(stack.size() < 2) {
								error = true;
								break;
							}

							long p1 = stack.pop();
							long p2 = stack.pop();
							stack.push(p1);
							stack.push(p2);
						} else if(curCommand.equals("ADD")) {
							if(stack.size() < 2) {
								error = true;
								break;
							}

							long p1 = stack.pop();
							long p2 = stack.pop();
							if(Math.abs(p1+p2)>MAX) {
								error = true;
							} else {
								stack.push(p1+p2);
							}
						} else if(curCommand.equals("SUB")) {
							if(stack.size() < 2) {
								error = true;
								break;
							}

							long p1 = stack.pop();
							long p2 = stack.pop();
							if(Math.abs(p2-p1)>MAX) {
								error = true;
								break;
							} else {
								stack.push(p2-p1);
							}
						} else if(curCommand.equals("MUL")) {
							if(stack.size() < 2) {
								error = true;
								break;
							}

							long p1 = stack.pop();
							long p2 = stack.pop();
							if(Math.abs(p1*p2)>MAX) {
								error = true;
								break;
							} else {
								stack.push(p1*p2);
							}
						} else if(curCommand.equals("DIV")) {
							if(stack.size() < 2) {
								error = true;
								break;
							}

							long p1 = stack.pop();
							long p2 = stack.pop();
							if(p1==0 || Math.abs(p2/p1)>MAX) {
								error = true;
								break;
							} else {
								stack.push(p2/p1);
							}
						} else if(curCommand.equals("MOD")) {
							if(stack.size() < 2) {
								error = true;
								break;
							}

							long p1 = stack.pop();
							long p2 = stack.pop();
							if(p1==0 || Math.abs(p2%p1)>MAX) {
								error = true;
								break;
							} else {
								stack.push(p2%p1);
							}
						}
					}

					if(error || stack.size() != 1) {
						System.out.println("ERROR");
						continue;
					} else {
						long answer = stack.peek();
						System.out.println(answer);
					}
				}
				System.out.println();
				commands.clear();
			}

			commands.add(command);
		}
	}
}
