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


# main
dongbin_solution()







