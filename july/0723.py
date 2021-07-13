# 정수 삼각형
def solution(triangle):
    for t in range(len(triangle) - 2, -1, -1):
        for idx in range(len(triangle[t])):
            val = max(triangle[t + 1][idx], triangle[t + 1][idx + 1])
            triangle[t][idx] += val
        print(triangle[t])

    return triangle[0][0]

#solution([[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]])

def solution(m, n, puddles):
    board = []
    for i in range(n):
        if i == 0:
            board.append([1 for j in range(m)])
        else:
            board.append([0 if j > 0 else 1 for j in range(m)])

    for p in puddles:
        board[p[1] - 1][p[0] - 1] = -1

    for r in range(1, n):
        for c in range(1, m):
            print(board[r-1][c],'~',board[r][c-1], end=' ')
            if board[r - 1][c] > 0 and board[r][c - 1] > 0:
                print("ㅋㅋㅋㅋㅋㅋㅋ")
                board[r][c] = board[r - 1][c] + board[r][c - 1]
            else:
                if board[r - 1][c] < 0:
                    print("ㅎㅎㅎㅎㅎㅎㅎ")
                    board[r][c] = board[r][c - 1]
                else:
                    print("aaaaaaaaaaa")
                    board[r][c] = board[r - 1][c]
        print()

    return board[n - 1][m - 1]

solution(4,3,[[2,2]])

