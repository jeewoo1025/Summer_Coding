# 백준 3020번 개똥벌레
import sys


if __name__ == '__main__':
    input = sys.stdin.readline

    # 입력
    N, H = map(int, input().rstrip().split())
    bottom = [0]*(H+1)  # 석순
    top = [0]*(H+1)     # 종유석

    for _ in range(N//2):
        bVal = int(input().rstrip())
        tVal = int(input().rstrip())

        bottom[bVal] += 1
        top[tVal] += 1

    # 누적합 Prefix
    totalBottom, totalTop = [0]*(H+1), [0]*(H+1)
    for i in range(H-1, 0, -1):
        totalBottom[i] = bottom[i] + totalBottom[i+1]
        totalTop[i] = top[i] + totalTop[i+1]

    minVal, count = N, 0
    for i in range(1, H+1):
        # 길이 i의 석순을 파괴할 때 파괴되는 모든 종유석과 석순의 합
        temp = totalBottom[i] + totalTop[H-i+1]
        if temp == minVal:
            count += 1
        elif temp < minVal:
            minVal = temp
            count = 1

    print(minVal, count)