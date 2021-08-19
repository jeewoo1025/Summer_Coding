# ↗ → ↘
dr = [-1, 0, 1]
dc = [1, 1, 1]

R, C = map(int, input().split())
visited = [[False for _ in range(C)] for _ in range(R)]

board = []
for _ in range(R):
    board.append(list(input()))


def dfs(point):
    r,c = point[0], point[1]
    visited[r][c] = True

    # 목적지인가?
    if c == C-1:
        return True

    # 순회
    for k in range(3):
        nextR = r+dr[k]
        nextC = c+dc[k]

        # 갈 수 있는가
        if nextR < 0 or nextR >= R or nextC < 0 or nextC >= C:
            continue

        if visited[nextR][nextC] or board[nextR][nextC] != '.':
            continue

        # 만약 갈 수 있으면 True 리턴
        flag = dfs((nextR, nextC))
        if flag:
            return True

    return False


answer = 0
for i in range(R):
    if dfs((i, 0)):
        answer += 1
print(answer)