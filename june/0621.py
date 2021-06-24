# 2번 큰수의 법칙
# 내림차순으로 나열 --> 큰 거부터 더하기 --> 만약 이동 시, <= 일때 k번, > 일 때 1번만 더하기

def jeewoo_solution1():
    N,M,K = map(int, input().split())
    nums = list(map(int, input().split()))
    nums.sort(reverse=True)
    print(nums)

    sum = 0
    idx = 0
    while M>0:
        if (M-K) < 0:
            cnt = M
        else:
            cnt = K

        sum += cnt*nums[idx]
        M -= cnt

        print("idx : ", idx)
        print("nums : ", nums[idx])
        print("sum : ", sum, end='\n\n')

        # 좌로 이동
        if idx > 0 and nums[idx-1] >= nums[idx]:
            idx -= 1
        else: # 우로 이동
            if nums[idx+1] == nums[idx]:
                idx += 1
            else:
                M -= 1
                sum += nums[idx+1]

    return sum


def dongbin_solution1():
    n,m,k = map(int, input().split())
    data = list(map(int, input().split()))

    data.sort()
    first = data[n-1]
    second = data[n-2]

    result = 0
    while True:
        for i in range(k):
            if m == 0:
                break
            result += first
            m -= 1
        if m == 0:
            break
        result += second
        m -= 1
    return result


# 3번 숫자 카드 게임 p.96
# 각 행의 최소값 중 가장 큰 수
def jeewoo_solution2():
    n,m = map(int, input().split())

    val = []
    for _ in range(n):
        tmp = list(map(int, input().split()))
        val.append(min(tmp))
    return max(val)


# 4번 1이 될 때까지 p.99
def jeewoo_solution3():
    n,k = map(int, input().split())

    cnt = 0
    while n > 1:
        if n%k == 0:
            n = n//k
        else:
            n -= 1
        cnt += 1

    return cnt


# CH4 구현
# 예제 4-1. 상하좌우  p.110
def jeewoo_solution4():
    N = int(input())
    order = input().split()

    # L,R,U,D
    dr = [0,0,-1,1]
    dc = [-1,1,0,0]

    now_r, now_c = 1,1
    for o in order:
        idx = 0
        if o == 'R':
            idx = 1
        elif o == 'U':
            idx = 2
        elif o == 'D':
            idx = 3

        r = dr[idx]
        c = dc[idx]

        if (r+now_r>=1 and r+now_r<=N) and \
            (c+now_c>=1 and c+now_c <=N):
            now_r += r
            now_c += c
            print(o, ':', now_r, now_c)
    print(now_r, now_c)


def donbin_solution4():
    n = int(input())
    x,y = 1,1
    plans = input().split()

    # L,R,U,D
    dx = [0,0,-1,1]
    dy = [-1,1,0,0]
    move_types = ['L', 'R', 'U', 'D']

    for plan in plans:
        for i in range(len(move_types)):
            if plan == move_types[i]:
                nx = x + dx[i]
                ny = y + dy[i]

        if nx < 1 or ny < 1 or nx > n or ny > n:
            continue
        x,y = nx, ny

    print(x,y)

from itertools import combinations

# 예제 4-2. 시각 p.113
def jeewoo_solution4_1():
    # 완전 탐색
    h = int(input())

    count = 0
    for i in range(h+1):
        for j in range(60):
            for k in range(60):
                if '3' in str(i) + str(j) + str(k):
                    count += 1

    print(count)


# 2번 왕실의 나이트 p.115
def jeewoo_solution4_2():
    n = input()
    x,y = n[0], n[1]
    x = ord(n[0]) - ord('a') + 1
    y = int(n[1])

    # 이동할 수 있는 경우의 수 : 8가지
    orders = [(2,1),(2,-1),(-2,1),(-2,-1),(1,2),(1,-2),(-1,2),(-1,-2)]
    cnt = 0

    for o in orders:
        dx = x + o[0]
        dy = y + o[1]

        if dx < 1 or dy < 1 or dx > 8 or dy > 8:
            continue
        print(o)
        cnt += 1
    print(cnt)


jeewoo_solution4_2()









