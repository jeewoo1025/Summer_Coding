# 그리디 알고리즘

def solution(number, k):
    stack = []

    for i in number:
        print('i : ', i)
        while stack and i > stack[-1]:
            if k > 0:
                stack.pop()
                k -= 1
            else:
                break
        stack.append(i)
        print('stack : ', stack, end='\n\n')
    print(k, '번')
    if k > 0:
        for i in range(k):
            stack.pop()

    answer = "".join(stack)
    return answer


def solution2(number, k):
    stack = [number[0]]

    for num in number[1:]:
        print('num : ', num)
        while stack and stack[-1] < num and k > 0:
            k -= 1
            stack.pop()
        stack.append(num)
        print('stack : ', stack, end='\n\n')

    if k != 0:
        stack = stack[:-k]
    return ''.join(stack)


print(solution2("1924", 2))


