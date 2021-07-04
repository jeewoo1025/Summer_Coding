
from collections import deque

grid = [[1,2],[3,4]]

# 상 하 좌 우
dx = [-1,1,0,0]
dy = [0,0,-1,1]


def bfs(x, y, graph):
    n,m = len(grid), len(grid[0])
    print(n,m)

    queue = deque()
    queue.append((x,y,graph[x][y]))
    visited = []
    answer = []

    while queue:
        x,y,val = queue.popleft()
        visited.append((x,y))
        print('pop : ', x+1, y+1, ', val : ', val)
        if x==n-1 and y==m-1:
            answer.append(val)
            continue

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue

            if not (nx,ny) in visited:
                temp = val + graph[nx][ny]
                queue.append((nx,ny,temp))

        print("queue : ", queue)
        print("graph : ", graph)
        print()

    return min(answer)


print(bfs(0,0,grid))
