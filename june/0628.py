# 프로그래머스 타겟넘버
import sys
from collections import deque

# 다음 인덱스에 해당하는 numbers 원소를 더하거나 뺀 값을 방문한다
# 이때, 방문한 원소 temp가 방문할 수 있는 값은
# 다음 인덱스에 위치한 numbers 원소를 + 또는 - 한 값이다
# 때문에 index도 같이 queue에 넣어주는 것이 편리하다

# bfs
def solution_target_number(numbers, target):
    result = 0
    queue = deque()
    print("queue : ", queue)
    queue.append([numbers[0], 0])
    queue.append([-1*numbers[0], 0])

    while queue:
        temp, idx = queue.popleft()
        idx += 1

        if idx < len(numbers):
            queue.append([temp + numbers[idx], idx])
            queue.append([temp - numbers[idx], idx])
        else:
            if temp == target:
                result += 1

    return result


# dfs
def solution2_dfs(numbers, target):
    answer = 0
    queue = [[numbers[0], 0], [-1*numbers[0],0]]

    while queue:
        temp, idx = queue.pop()
        idx += 1

        if idx < len(numbers):
            queue.append([temp+numbers[idx], idx])
            queue.append([temp-numbers[idx], idx])
        else:
            if temp == target:
                answer += 1
    return answer


# 이진탐색
def binary_search(graph, target):
    start, end = 0, (len(graph)-1)
    while start <= end:
        mid = (start+end)//2
        if graph[mid]==target:
            return graph[mid]
        elif graph[mid] > target:
            end = mid-1
        else:
            start = mid+1
    return None


# 2번 부품찾기 - p.197
def find_items():
    N = int(input())
    graph = list(map(int, input().split()))
    graph.sort()

    M = int(input())
    targets = list(map(int, input().split()))

    for t in targets:
        tmp = binary_search(graph, t)
        if tmp:
            print("yes", end=' ')
        else:
            print("no", end=' ')
    print()


# 3번 떡볶이 떡 p.201
def make_dduk():
    N,M = map(int, input().split())
    dduks = list(map(int, input().split()))

    start = 0
    end = max(dduks)

    result = 0
    while start<=end:
        total = 0
        mid = (start+end)//2

        # 잘린 떡 계산
        for x in dduks:
            if x > mid:
                total += (x-mid)

        if total < M:  # 더 자르기
            end = mid-1
        else:  #덜자르기 --> 키우기
            result = mid
            start = mid+1
    print(result)


make_dduk()