import sys
from collections import deque


def check(tmp_r, tmp_c, tmp_k):  # 갈 수 있는가?
    if 0<=tmp_r<H and 0<=tmp_c<W:
        if visited[tmp_k][tmp_r][tmp_c]==False and board[tmp_r][tmp_c]==0:
            return True
    return False


# 말의 이동방식
horseDir = [(-2,1),(-1,2),(1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1)]

# 인접 : 상,하,좌,우
nearDir = [(-1,0),(1,0),(0,-1),(0,1)]

# 입력
K = int(input())
W,H = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(H)]

# 초기화
answer = sys.maxsize

# visited[k][r][c] : 말을 k번 이동한 경우의 방문 여부
visited = [[[False]*W for _ in range(H)] for _ in range(K+1)]

queue = deque()
queue.append([0,0,0,K,'(0,0)']) # 좌표, 갯수, x번 이동 가능 횟수

while queue:
    r,c,count,k,history = queue.popleft()

    if (r,c) == (H-1, W-1):     # 종점이면 빠져나감
        # print(count, ' = ', history)
        answer = min(answer,count)
        break

    if k > 0:     # x 이동가능한가?
        for d in range(len(horseDir)):
            nr = r + horseDir[d][0]
            nc = c + horseDir[d][1]
            if check(nr, nc, k-1):
                visited[k-1][nr][nc] = True
                queue.append([nr,nc,count+1,k-1,history+' → 말(%d,%d)'%(nr,nc)])

    # 상,하,좌,우 탐색
    for d in range(len(nearDir)):
        nr = r + nearDir[d][0]
        nc = c + nearDir[d][1]
        if check(nr, nc, k):
            visited[k][nr][nc] = True
            queue.append([nr,nc,count+1,k,history+' → 인(%d,%d)'%(nr,nc)])


# 최소 거리 출력하기
if answer == sys.maxsize:
    print(-1)
else:
    print(answer)
