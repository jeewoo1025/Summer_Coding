import sys
from collections import deque


def spread():
    size = len(waterQ)

    while size > 0:
        waterPos = waterQ.popleft()

        for d in range(4):
            nr = waterPos[0] + dr[d]
            nc = waterPos[1] + dc[d]

            # 범위 안이고 비어있는 칸
            if 0<=nr<R and 0<=nc<C and board[nr][nc]=='.':
                waterQ.append((nr,nc))
                board[nr][nc] = '*'

        size -= 1


def bfs():
    size = len(moveQ)

    while size > 0:
        pos = moveQ.popleft()

        for d in range(4):
            nr = pos[0] + dr[d]
            nc = pos[1] + dc[d]

            # 갈 수 있는가? = 범위 안, 이동가능
            if 0>nr or nr>=R or 0>nc or nc>=C:
                continue
            if board[nr][nc]=='.' or board[nr][nc]=='D':
                # 비버굴 발견!
                if board[nr][nc]=='D':
                    moveQ.clear()
                    return move

                # 방문 X
                if not visited[nr][nc]:
                    moveQ.append((nr,nc))
                    visited[nr][nc] = True
        size -= 1
    return sys.maxsize


# main
input = sys.stdin.readline

# 상 하 좌 우
dr = [-1,1,0,0]
dc = [0,0,-1,1]

R,C = map(int, input().split())

# 변수 초기화
board = []
visited = [[False]*C for _ in range(R)]
moveQ = deque()
waterQ = deque()

for r in range(R):
    board.append(list(input().rstrip()))
    for c in range(C):
        if board[r][c] == 'S':  # 고슴도치
            moveQ.append((r,c))
            visited[r][c] = True
        elif board[r][c] == '*':    # 물
            waterQ.append((r,c))

move = 0    # 이동횟수
answer = sys.maxsize

# 한 cycle에 물의 큐와 고슴도치의 큐 크기만큼 돌린다.
while moveQ:
    # 물 퍼짐
    spread()

    # 고슴도치 이동
    move += 1
    answer = bfs()

print('KAKTUS' if answer==sys.maxsize else move)