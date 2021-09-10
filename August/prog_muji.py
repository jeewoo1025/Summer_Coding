import heapq


def solution(food_times, k):
    if sum(food_times) <= k:
        return -1

    # 시간이 작은 음식부터 제거
    q = []
    for i in range((len(food_times))):
        # 음식시간, 음식번호
        heapq.heappush(q, (food_times[i], i + 1))  # 최소힙

    sum_val = 0  # 먹기 위해 사용한 시간
    previous = 0  # 직전에 다 먹은 음식 시간
    length = len(food_times)  # 남은 음식 갯수

    # 총 시간 + (현재 음식 시간-이전 음식시간)*현재 음식 갯수
    # 무조건 previous < now
    while sum_val + ((q[0][0] - previous) * length) <= k:
        now = heapq.heappop(q)[0]
        sum_val += (now - previous) * length
        length -= 1  # 다 먹은 음식 제외
        previous = now  # 이전 음식 시간

    # 남은 음식 중에서 몇 번째 음식인지 확인하여 출력
    result = sorted(q, key=lambda x: x[1])  # 번호 순

    # 남은시간%남은 갯수
    answer = result[(k - sum_val) % length]  # (시간, 번호)
    return answer[1]