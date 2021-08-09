import sys
from collections import deque

# 위, 아래, 좌, 우, 대각선
dr = [-1,1,0,0,-1,1,1,-1]
dc = [0,0,-1,1,-1,-1,1,1]

# BFS + 투 포인터
N = int(input())
board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
altitude = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]

houses = 0
fatigue = []

for i in range(N):
    for j in range(N):
        if board[i][j] == 'P':
            px, py = i, j
        elif board[i][j] == 'K':
            houses += 1
        fatigue.append(altitude[i][j])

# 1. 입력받은 pirodo에 대해서 정렬 and set을 통한 중복 제거
fatigue = sorted(set(fatigue))
#print("fatigue : ", fatigue)

left, right = 0,0
answer = sys.maxsize


def bfs(start_x,start_y):
    visit = [[False]*N for _ in range(N)]
    queue = deque([(start_x,start_y)])
    visit[start_x][start_y] = True
    K = 0   # 방문한 집의 갯수

    while queue:
        # print("queue : ", queue)
        x,y = queue.popleft()
        #print("{}, {} pop".format(x,y))

        # 수직, 수평, 대각선 탐색
        for i in range(8):
            nx,ny = x+dr[i], y+dc[i]

            if nx<0 or nx>=N or ny<0 or ny>=N:
                continue
            if visit[nx][ny]:
                continue

            tired = altitude[nx][ny]

            # 현재 피로도가 left, right 사이일 경우에만 추가로 탐색
            if fatigue[left] <= tired <= fatigue[right]:
                #print("좌표={},{} , 피로도={} ({}~{})".format(nx,ny,tired,fatigue[left],fatigue[right]))

                visit[nx][ny] = True
                queue.append((nx,ny))

                # 집이면 K 증가
                if board[nx][ny] == 'K':
                    K += 1
    return K


while left<len(fatigue):
    #print("left : ", left,", right : ",right, end='  - ')
    #print("{} ~ {}".format(fatigue[left], fatigue[right]))
    K=0

    # 3. 시작점의 고도가 최대 고도와 최소 고도 사이일 경우에만 BFS를 시작
    if fatigue[left] <= altitude[px][py] <= fatigue[right]:
        #print("BFS start!")
        K = bfs(px, py)

    # 집을 모두 방문함
    if K == houses:
        # 최소 피로도 구하기
        answer = min(answer, fatigue[right]-fatigue[left])
        #print("집을 모두 방문! answer : ", answer)

        left += 1  # 최소 높이 증가
    elif (right+1) < len(fatigue):
        # 아직 최대고도가 남아있을 때 최대 고도 증가
        #print("아직 최대 고도가 남음.. ")
        right += 1
    else:
        #print("모두 아닐 때,, ")
        break
    #print()

print(answer)