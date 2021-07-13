# 정수 삼각형
def solution(triangle):
    for t in range(len(triangle) - 2, -1, -1):
        for idx in range(len(triangle[t])):
            val = max(triangle[t + 1][idx], triangle[t + 1][idx + 1])
            triangle[t][idx] += val
        print(triangle[t])

    return triangle[0][0]

solution([[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]])