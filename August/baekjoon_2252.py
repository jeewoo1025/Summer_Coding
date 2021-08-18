# 백준 2252번 줄 세우기
# 위상 정렬
from collections import deque

N, M = map(int, input().split())

# 그래프 초기화
edge = [[] for _ in range(N+1)]
indegree = [0 for _ in range(N+1)]

for i in range(M):
    a, b = map(int, input().split())

    # a < b
    edge[a].append(b)
    indegree[b] += 1

# 진입 차수 0인 얘들 큐에 넣기
queue = deque()
for i in range(1, N+1):
    if indegree[i] == 0:
        queue.append(i)

# 큐에서 1개씩 뽑으면서 연결된 간선 끊기
while len(queue)>0:
    now = queue.popleft()
    print(now, end=' ')

    # 연결된 간선 끊기
    for t in edge[now]:
        indegree[t] -= 1
        if indegree[t] == 0:
            queue.append(t)
