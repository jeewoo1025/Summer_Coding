import sys
from collections import deque

# 상 하 좌 우
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

N,M = map(int, input().split())
board = [list(map(int, input())) for _ in range(N)]
visited = [[[True]*2 for _ in range(M)] for _ in range(N)]
queue = deque()
answer = sys.maxsize

# 초기값 : 좌표 (0,0), 거리 1, 벽 가능 1
queue.append([0,0,1,1])

while queue:
    y,x,dist,cnt = queue.popleft()

    if y==(N-1) and x==(M-1):
        answer = dist
        break

    for i in range(4):
        ny = y + dr[i]
        nx = x + dc[i]

        if 0<=ny<N and 0<=nx<M and visited[ny][nx][cnt]:
            if board[ny][nx] == 0:  # 이동 가능
                queue.append([ny, nx, dist+1, cnt])
                visited[ny][nx][cnt] = False

            # 벽을 부수지 않은 상태에서 방문함 --> 벽을 부숨
            if cnt==1 and board[ny][nx]==1:
                queue.append([ny, nx, dist+1, 0])  # cnt 1-->0 변경
                visited[ny][nx][0] = False

if answer == sys.maxsize:
    print(-1)
else:
    print(answer)
