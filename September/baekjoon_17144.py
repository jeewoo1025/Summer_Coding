import math

# 상, 하, 좌, 우
dr = [-1,1,0,0]
dc = [0,0,-1,1]

R, C, T = map(int, input().split())

board = []
for r in range(R):
    board.append(list(map(int, input().split())))

def printBoard():
    for b in board:
        for c in b:
            print(c, end=' ')
        print()

def spreadDust(airTop, airDown, origin):
    results = [[0]*C for _ in range(R)]
    for r in range(R):
        for c in range(C):
            if origin[r][c] <= 0:  # 없거나 공기청정기
                continue

            # 미세먼지 확산!
            curDust = origin[r][c]
            totalDir = 0
            for d in range(4):  # 상하좌우 탐색
                nr = r + dr[d]
                nc = c + dc[d]

                if nr<0 or nr>=R or nc<0 or nc>=C:  # 범위 밖인가?
                    continue
                if (nr, nc) == airTop or (nr, nc) == airDown:  # 공기청정기인가?
                    continue

                results[nr][nc] += math.trunc(curDust/5)
                totalDir += 1

            if totalDir > 0: # 현재 미세먼지 양 update
                results[r][c] += curDust-math.trunc(curDust/5)*totalDir
    return results


def check(r, c, origin):
    # 범위 밖인가?
    if r<0 or r>=R or c<0 or c>=C:
        return False

    # 공기청정기 원래 위치인가?
    if r == origin[0] and c == origin[1]:
        return False
    return True


def workAirPurifier(airLoc, dir):
    r, c = airLoc[0], airLoc[1]
    before, curDir = 0, 0

    while curDir < 4:
        # 범위 내일 때까지 현재 방향대로 증가
        while check(r+dir[curDir][0], c+dir[curDir][1], airLoc):
            # 위치 update
            r += dir[curDir][0]
            c += dir[curDir][1]

            # 현재 위치에 이전값 넣기
            cur = board[r][c]
            board[r][c] = before

            # before update
            before = cur
        curDir += 1

    board[airLoc[0]][airLoc[1]] = 0


# 공기청정기 위치 알기
temp = []
for r in range(R):
    if board[r][0] == -1:
        temp.append(r)
        board[r][0] = 0
airTop, airDown = (temp[0],0), (temp[1],0)

# 1초 ~ T초 반복
for t in range(T):
    # 1. 미세먼지가 있는 모든 칸에서의 확산
    board = spreadDust(airTop, airDown, board)

    # 2. 공기청정기 작동
    # 2-1. 위쪽은 반시계 방향으로 -> 우, 상, 좌, 하
    airTopDir = [(0,1),(-1,0),(0,-1),(1,0)]
    workAirPurifier(airTop, airTopDir)

    # 2-2. 아래쪽은 시계 방향으로 -> 우, 하, 좌, 상
    airDownDir = [(0,1),(1,0),(0,-1),(-1,0)]
    workAirPurifier(airDown, airDownDir)

# 총 미세먼지 양 구하기
answer = 0
for b in board:
    answer += sum(b)
print(answer)