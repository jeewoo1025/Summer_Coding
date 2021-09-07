def solution(expression):
    operations = [('+', '-', '*'),('+', '*', '-'),('-', '+', '*'),('-', '*', '+'),('*', '+', '-'),('*', '-', '+')]
    answer = []

    for op in operations:
        print(op)
        a = op[0]
        b = op[1]
        temp_list = []

        for e in expression.split(a):
            print('e : ', e, end='  --> ')
            temp = [f"({i})" for i in e.split(b)]
            print('temp : ', temp)
            temp_list.append(f'({b.join(temp)})')

        print('수식 : ', a.join(temp_list), end='\n\n')
        answer.append(abs(eval(a.join(temp_list))))
    return max(answer)


solution("100-200*300-500+20")