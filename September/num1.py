def solution(sticker):
    if len(sticker) == 1:
        return sticker[0]
    N = len(sticker)

    # 0번 스티커 뗌
    dp = [0] * N
    dp[0] = sticker[0]
    dp[1] = dp[0]

    for i in range(2, N - 1):
        dp[i] = max(dp[i - 1], dp[i - 2] + sticker[i])
    answer = max(dp)

    # 1번 스티커 뗌
    dp = [0] * N
    dp[1] = sticker[1]
    for i in range(2, N):
        dp[i] = max(dp[i - 1], dp[i - 2] + sticker[i])

    return max(answer, max(dp))