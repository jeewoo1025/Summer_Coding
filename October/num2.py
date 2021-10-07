def solution(n, left, right):
    answer = [x for x in range(left, right + 1)]

    for idx, x in enumerate(answer):
        # 행,열 구하기
        row = x // n
        col = x % n

        # max(행, 열)+1
        answer[idx] = max(row, col) + 1

    return answer