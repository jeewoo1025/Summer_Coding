def simulation(start, queries, n, m):  # 시작점, 쿼리들, n, m
    commands = [(0, -1), (0, 1), (-1, 0), (1, 0)]  # 좌,우,위,아래
    i, j = start[0], start[1]

    for c, dx in queries:
        if (i, c) == (0, 2) or (i, c) == (n - 1, 3):
            continue
        if (j, c) == (0, 0) or (j, c) == (m - 1, 1):
            continue
        i += commands[c][0] * dx
        j += commands[c][1] * dx

        # 범위 밖인가?
        if i >= n:
            i = n - 1
        elif i < 0:
            i = 0

        if j >= m:
            j = m - 1
        elif j < 0:
            j = 0
    return (i, j)


def solution(n, m, x, y, queries):
    answer = 0
    for i in range(n):  # 행
        for j in range(m):  # 열
            # print(i,j)
            value = simulation((i, j), queries, n, m)
            if (x, y) == value:
                answer += 1
    return answer