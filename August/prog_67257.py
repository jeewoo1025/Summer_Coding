from itertools import permutations

# 중위 표현식 -> 후위 표현식
def infixTopostfix(tokens, prec):
    stack = []
    postfix = []

    # 2. 중위 표현식을 왼쪽부터 한 글자씩 읽음
    for token in tokens:
        # 3. 피연산자이면 리스트에 append
        if type(token) is int:
            postfix.append(token)
        else:
            # 스택이 비어있을 경우 스택에 push
            if not stack:
                stack.append(token)
            else:
                # 4. 연산자이면 스택에서 이보다 높은 우선순위들 POP
                while stack:
                    if prec[token] <= prec[stack[-1]]:
                        postfix.append(stack.pop())
                    else:
                        break

                stack.append(token)

    # 6. 스택에 남아있는 연산자는 모두 POP. 리스트에 append
    while stack:
        postfix.append(stack.pop())

    return postfix


# 후위 표기법 계산
def calPostfix(tokens):
    stack = []

    for token in tokens:
        if type(token) is int:
            stack.append(token)
            continue

        num1 = stack.pop()
        num2 = stack.pop()
        if token == '*':
            stack.append(num2 * num1)
        elif token == '+':
            stack.append(num2 + num1)
        else:  # '-'
            stack.append(num2 - num1)

    return stack.pop()


def solution(expression):
    tokens = []

    # 연산자, 피연산자 입력
    stack = []
    for e in expression:
        if "+-*".find(e) != -1:  # 연산자
            tokens.append(int(''.join(stack)))   # 숫자 변형
            stack.clear()
            tokens.append(e)
        else:
            stack.append(e)
    tokens.append(int(''.join(stack)))

    # 연산자 우선순위 지정
    op = ['+', '-', '*']
    for i in op:
        if not i in expression:
            op.remove(i)

    answer = 0
    for i in map(list, permutations(op)):  # 순열
        prec = {i:j for j,i in enumerate(list(i))}

        postfix = infixTopostfix(tokens, prec)
        result = calPostfix(postfix)
        print("prec : ", prec, '  -> ', result)

        answer = max(answer, abs(result))
    print(answer)
    return answer


solution("100-200*300-500+20")