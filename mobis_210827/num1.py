def getCount(dice, obj):  # 배열, 숫자
    total = 0
    for i in range(len(dice)):
        if obj in dice[i]:
            total += 1
    return total


def solution(dice):
    for i in range(1, len(dice)+1):  # 1 ~ N
        if getCount(dice, 0) < (i-1):
            return int('1'+'0'*(i-1))

        for j in range(1, 10):
            if getCount(dice, j) < i:
                return int(str(j)*i)
    return 10000


#tmp = [[0, 1, 5, 3, 9, 2], [2, 1, 0, 4, 8, 7], [6, 3, 4, 7, 6, 5]]
tmp =[[1, 6, 2, 5, 3, 4], [9, 9, 1, 0, 7, 8]]
print(solution(tmp))
