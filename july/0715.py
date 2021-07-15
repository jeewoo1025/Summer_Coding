# 단속 카메라
def solution(routes):
    answer = 0
    routes = sorted(routes, key=lambda x: x[1])
    print(routes)

    camera = -30001

    for r in routes:
        if camera < r[0]:
            answer += 1
            camera = r[1]
            print(r)
            print("new camera : ", camera)
    return answer


#print(solution([[-20,15], [-14,-5], [-18,-13], [-5,-3]]))

# 짝지어 제거하기
def solution(s):
    stack = []
    for i in s:
        if len(stack) == 0:
            stack.append(i)
        elif stack[-1] == i:
            stack.pop()
        else:
            stack.append(i)

    if len(stack) == 0:
        return 1
    return 0