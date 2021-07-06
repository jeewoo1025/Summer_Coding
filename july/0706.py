# 네이버 체험형 인턴 Language AI

# 3번째 문제
def solution(S):
    if len(S) <= 3 or (S.count('a') % 3) != 0:
        return 0

    result = 0
    limit_a = (S.count('a') // 3)  # a의 제한 갯수
    cnt = (len(S) // 3) * 3
    for c in range(cnt, 0, -1):
        tmp1 = S[0:c]
        if tmp1.count('a') != limit_a:
            continue

        # 나머지 그룹
        for i in range(1, (len(S) - c)):
            tmp2 = S[c:c + i]
            tmp3 = S[c + i:]

            if tmp2.count('a') == limit_a and tmp3.count('a') == limit_a:
                result += 1
                print("c : {} ~ {} = {}".format(0, c, tmp1))
                print("{} ~ {} = {}".format(c, c+i, tmp2))
                print("{} ~ {} = {}".format(c+i, len(S), tmp3))
                print()

    print("result : ", result)
    return result


# 1번째 문제
def solution(S):
    answer = []

    data_list = S.split('\n')
    answer.append(data_list.pop(0))

    for data in data_list:
        if not 'NULL' in data.split(','):
            answer.append(data)
    return '\n'.join(answer)


# 2번째 문제
indices = [1,2,3,4,5,6,7,8,9,10,11]
K = 3
gap = len(indices)//K

result = []
start = 0
for k in range(K):
    end = start + gap
    if end >= len(indices):
        end = len(indices)-1

    print(start, '~', end)
    test = indices[start:end+1]
    result.append([i for i in indices if not i in test])
    result.append(test)

    print('test : ', test)
    print('train : ', [i for i in indices if not i in test])
    print()

    start += gap+1


print(result)









