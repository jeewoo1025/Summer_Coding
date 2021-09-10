N = int(input())
max_value = 0

T,P=[],[]
for i in range(N):
    t,p = map(int, input().split())
    T.append(t)
    P.append(p)


dp = [0]*(N+1)
for i in range(N-1, -1, -1):
    time = T[i] + i

    # 상담이 기간 안에 끝난 경우
    if time <= N:
        dp[i] = max(P[i]+dp[time], max_value)
        max_value = dp[i]
    # 상담기간을 벗어나는 경우
    else:
        dp[i] = max_value

print(dp)
print(max_value)