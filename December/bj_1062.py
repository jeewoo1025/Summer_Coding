import sys


def howMany(alpha, cnt):    # 익힌 알파벳과 갯수
    global answer

    if cnt == K:
        wordCount = 0       # 익을 수 있는 문장 갯수
        for i in range(0, N):
            flag = True
            for word in strList[i]:
                idx = ord(word) - ord('a')
                if not visited[idx]:
                    flag = False
                    break

            if flag:
                wordCount += 1

        if wordCount > answer:
            answer = wordCount
        return

    # 브루트 포스
    for i in range(alpha, 26):
        if not visited[i]:
            visited[i] = True
            howMany(i, cnt+1)
            visited[i] = False


if __name__ == '__main__':
    input = sys.stdin.readline
    answer = 0

    N, K = map(int, input().rstrip().split())
    strList = []
    for _ in range(N):
        temp = input().rstrip()
        strList.append(temp[4:-4])

    if K < 5:   # a,n,t,i,c 미만
        print('0')
    elif K == 26:   # 전부 배울 수 있음
        print(N)
    else:
        K -= 5
        visited = [False]*26

        ordA = ord('a')
        visited[ord('a') - ordA] = True
        visited[ord('n') - ordA] = True
        visited[ord('t') - ordA] = True
        visited[ord('i') - ordA] = True
        visited[ord('c') - ordA] = True

        howMany(0, 0)
        print(answer)