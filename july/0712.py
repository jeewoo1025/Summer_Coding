
def solution(N, board):
    answer = 0

    # 가장자리만 돌기
    for r in range(N):
        for c in range(N):
            if board[r][c] == 1:
                continue

            if (r,c) in [(0,0), (N-1,N-1), (0,N-1), (N-1,0)]:
                continue

            if r<(N-1) and r>0:
                if c%N!=0 or (c+1)%N!=0:
                    continue

            print("현재 좌표 : {}, {}".format(r,c))
            # 왼 오 위 아래 코어 있는지 확인하기
            if c == 0 or c == N - 1:
                # 아래/위 확인하기
                val = 1
                if r==N-1:
                    val = -1
                for i in range(1,N):
                    print('첫번째 :',board[r][c + i * val], end=' ')
                    if board[r][c+i*val] == 1:
                        answer += i
                        print("answer : ", answer)
                        break
                print()
            elif r == 0 or r == N - 1:
                val = 1
                if c==N-1:
                    val = -1

                # 오른쪽/왼쪽 확인하기
                for i in range(1,N):
                    print('두번째 :',board[r+i*val][c], end=' ')
                    if board[r+i*val][c] == 1:
                        answer += i
                        print("answer : ", answer)
                        break
                print()


# main
T = int(input())
for t in range(T):
    N = int(input())
    board = []
    for _ in range(N):
        board.append(list(map(int, input().split())))

    answer = solution(N, board)
    print("#{} {}".format(t+1, answer))
print()