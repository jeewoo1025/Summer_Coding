def solution(rows, cols, queries):
    answer = []
    board = [[] for _ in range(rows)]

    for i in range(1, rows+1):
        for j in range(1, cols+1):
            board[i-1].append((i-1)*cols+j)
            print(board[i-1])

    for x1,y1,x2,y2 in queries:
        temp = board[x1-1][y1-1]
        mini = temp

        #왼쪽 세로
        for k in range(x1-1, x2-1):
            board[k][y1-1] = board[k+1][y1-1]
            mini = min(mini, board[k][y1-1])

        for k in range(y1-1, y2-1):
            board[x2-1][k] = board[x2-1][k+1]
            mini = min(mini, board[x2-1][k])

        for k in range(x2-1,x1-1,-1):
            board[k][y2-1] = board[k-1][y2-1]
            mini = min(mini, board[k][y2-1])

        for k in range(y2-1,y1-1,-1):
            board[x1-1][k] = board[x1-1][k-1]
            mini = min(mini, board[x-1][k])

        board[x1-1][y1] = temp
        answer.append(mini)

    return answer


solution(3,3,[1,1,2,3])