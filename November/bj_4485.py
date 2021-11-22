# 백준 4485번 녹색 옷 입은 젤다
import sys
from heapq import heappush, heappop

# 상,하,좌,우
dir = [(-1,0),(1,0),(0,-1),(0,1)]
INF = sys.maxsize
input = sys.stdin.readline


def dij(cnt):
    d,q = [[INF]*N for _ in range(N)], []
    heappush(q, [board[0][0], 0, 0])
    d[0][0] = 0

    while q:
        # 최단 경로 중 가장 짧은 노드에 대한 정보 꺼내기
        w,x,y = heappop(q)

        if x==N-1 and y==N-1:
            print("Problem {0}: {1}".format(cnt, w))
            break

        # 현재 노드와 연결된 인접한 노드 확인 (상,하,좌,우)
        for i in range(4):
            nx = x + dir[i][0]
            ny = y + dir[i][1]

            if 0<=nx<N and 0<=ny<N:  # 범위 안
                nw = w + board[nx][ny]
                if nw < d[nx][ny]:
                    d[nx][ny] = nw
                    heappush(q, [nw,nx,ny])


cnt = 1
while True:
    N = int(input())
    if N==0:
        break

    board = [list(map(int, input().split())) for _ in range(N)]
    dij(cnt)
    cnt += 1
