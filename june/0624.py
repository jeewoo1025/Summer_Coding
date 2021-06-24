# 3. 게임개발  p.118
def jeewoo_solution():
    N,M = map(int, input().split())
    x,y,d = map(int, input().split())

    # 북, 동, 남, 서
    dx = [0, 1, 0, -1]
    dy = [-1, 0, 1, 0]

    board = []
    for _ in range(N):
        board.append(input().split())
    board[y][x] = '1'

    # 탐험하기
    cnt = 1
    while True:
        flag = False

        # 왼쪽 방향
        for i in range(4):
            d -= 1   # 반시계방향
            if d < 0:
                d = 3

           # print("d : ", d)
            tmp_x = x + dx[d]
            tmp_y = y + dy[d]
           # print("{}, {} - {}".format(tmp_x, tmp_y, board[tmp_y][tmp_x]), end='\n\n')

            if tmp_y>=0 and tmp_x >= 0 and board[tmp_y][tmp_x]=='0':
                cnt += 1

                x = tmp_x
                y = tmp_y
                board[y][x] = '1'
                flag = True

                # 디버깅
                for idx in range(N):
                    print(board[idx])
                print()

        # 모두 바다 or 가본 곳
        if not flag:
            x = x - dx[d]
            y = y - dx[d]

            if board[y][x] == '1':
                break

    print(cnt)


def dongbin_solution():
    n, m = map(int, input().split())
    d = [[0]*m for _ in range(n)]

    x,y,direction = map(int, input().split())
    d[x][y] = 1

    arr = []
    for i in range(n):
        arr.append(list(map(int, input().split())))

    # 북,동,남,서 방향 정의
    dx = [-1,0,1,0]
    dy = [0,1,0,-1]

    # 시뮬레이션 시작
    count = 1
    turn_time = 0
    while True:
        # 왼쪽으로 회전
        turn_left()
        nx = x + dx[direction]
        ny = y + dy[direction]

        # 회전한 이후, 정면에 가보지 않은 칸이 존재하는 경우 이동
        if d[nx][ny] == 0 and arr[nx][ny] == 0:
            d[nx][ny] = 1
            x = nx
            y = ny
            count += 1
            turn_time = 0
            continue
        else:  #회전한 이후 정면에 가보지 않은 칸이 없거나 바다인 경우
             turn_time += 1

        # 네 방향 모두 갈 수 없는 경우
        if turn_time == 4:
            nx = x - dx[direction]
            ny = y - dy[direction]

            # 뒤로 갈 수 있다면 이동하기
            if arr[nx][ny] == 0:
                x = nx
                y = ny
            else:   # 뒤로 바다가 막혀있는경우
                break
            turn_time = 0

    print(count)


def turn_left():
    global direction
    direction -= 1
    if direction == -1:
        direction = 3


# DFS
def dfs(graph, v, visited):
    # 현재 노드를 방문처리
    visited[v] = visited
    print(v, end=' ')

    # 현재 노드와 연결된 다른 노드를 재귀적으로 방문
    for i in graph[v]:
        if not visited[i]:
            dfs(graph, i, visited)

# BFS
from collections import deque

def bfs(graph, start, visited):
    queue = deque([start])
    visited[start] = visited

    while queue:
        v = queue.popleft()
        print(v, end=' ')
        # 해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True


graph = [
    [],
    [2,3,8],
    [1,7],
    [1,4,5],
    [3,5],
    [3,4],
    [7],
    [2,6,8],
    [1,7]
]

visited = [False]*9
#dfs(graph, 1, visited)

# main
#dongbin_solution()


# 3. 음료수 얼려먹기 p.149
def jeewoo_solution2():
    N,M = map(int, input().split())

    board = []
    for _ in range(N):
        board.append(list(map(int, input())))


    # DFS
    cnt = 0
    # 왼,오,위,아래
    dr = [0,0,-1,1]
    dc = [-1,1,0,0]

    for r in range(N):
        for c in range(M):
            if board[r][c] == 0:
                stack = [(r,c)]
                board[r][c] = 2

                print("현재 좌표 : {}, {}".format(r,c))
                print('==== start stack ====')
                # DFS 탐색
                while stack:
                    turn_time = 0
                    r, c = stack[-1]

                    for i in range(4):
                        nr = r+dr[i]
                        nc = c+dc[i]

                        if nr<0 or nc<0 or nr>=N or nc>=M:
                            turn_time += 1
                            continue
                        if board[nr][nc] == 0:
                            r = nr
                            c = nc
                            board[r][c] = 2
                            stack.append((r,c))
                            break
                        else:
                            turn_time += 1

                    if turn_time == 4:
                        stack.pop()
                    print("stack : ", stack)
                print('==== finish stack ====', end='\n\n')

                # 디버깅
                for i in range(N):
                    print(board[i])
                print()

                cnt += 1

    print(cnt)


def dongbin_solution2():
    n,m = map(int, input().split())

    graph = []
    for i in range(n):
        graph.append(list(map(int, input())))

    # DFS로 특정한 노드를 방문한 뒤에 연결된 모든 노드들도 방문
    def dfs(x,y):
        if x<=-1 or x>=n or y<=-1 or y>=m:
            return False
        # 현재 노드를 방문하지 않았다면
        if graph[x][y] == 0:
            # 해당 노드를 방문처리
            graph[x][y] = 1

            # 상,하,좌,우를 재귀적으로 호출
            dfs(x-1,y)
            dfs(x,y-1)
            dfs(x+1,y)
            dfs(x,y+1)
            return True
        return False


    # 모든 노드(위치)에 대하여 음료수 채우기
    result = 0
    for i in range(n):
        for j in range(m):
            if dfs(i,j) == True:
                result += 1
    print(result)


jeewoo_solution2()




















