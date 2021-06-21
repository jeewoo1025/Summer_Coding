h, w = map(int, input().split())
board = [[0 for _ in range(w)] for _ in range(h)]

for r in range(h):
    for c in range(w):
        print(board[r][c], end=' ')
    print()

n = int(input())
for _ in range(n):
    l, d, x, y = map(int, input().split())
    x -= 1
    y -= 1
    print("l : ", l)
    print("{}, {}".format(x,y))

    for i in range(l):
        print("i : ", i)
        if d == 0:
            board[x][y + i] = 1
        else:
            board[x + i][y] = 1

for r in range(h):
    for c in range(w):
        print(board[r][c], end=' ')
    print()