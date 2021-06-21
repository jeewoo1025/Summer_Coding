# 다이나믹 프로그래밍

# 1번 사용 - 5
# 2번 사용 - 55, 5+5, 5/5, 5*5, 5/5
# ... 3번쩨 원소는 N을 3개 사용해서 만들 수 있는 집합
# 만약 계산된 수에서 number가 있다면 그 수의 인덱스에 +1 반환, 없는 경우 -1

# set() : 비어있는 집합 자료형 생성
# set.add() : 집합에 값 추가하기

# 집합이라 중복 추가 X

def solution(N, number):
    if N==number:
        return 1

    answer = -1
    S = [set() for _ in range(8)]
    for i, x in enumerate(S, start=1):
        x.add(int(str(N)*i))

    for i in range(1, len(S)):
        for j in range(i):
            for op1 in S[j]:
                for op2 in S[i-j-1]:
                    # 더하기
                    S[i].add(op1 + op2)

                    # 빼기
                    S[i].add(op1 - op2)

                    # 곱하기
                    S[i].add(op1*op2)

                    # 나누기
                    if(op2):
                        S[i].add(op1//op2)

        if number in S[i]:
            answer = i+1
            break
    return answer


camels = [200, 300]
dest = [1,2]

min_idx = dest.index(min(dest))
camels.append(dest.pop(min_idx))
print("camels : ", camels)
print("dest : ", dest)

