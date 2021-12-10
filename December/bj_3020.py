# 백준 3020번 개똥벌레
import sys


if __name__ == '__main__':
    input = sys.stdin.readline

    N, H = map(int, input().rstrip().split())
    obstacle = [0]*(H+1)
    obstacle[0] = 200001

    for _ in range(N//2):
        x = int(input().rstrip())   # 석순    1~x
        for i in range(1, x+1):
            obstacle[i] += 1

        y = int(input().rstrip())   # 종유석   (H-y+1)~H
        for i in range((H-y+1), H+1):
            obstacle[i] += 1

    answer = min(obstacle)
    print(answer, end=' ')
    print(obstacle.count(answer))
