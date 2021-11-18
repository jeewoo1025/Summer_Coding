# 백준 14398번 서강그라운드
import sys

# 입력
INF = sys.maxsize

N,M,R = map(int, input().split())
items = list(map(int, input().split()))
items.insert(0, -1)

d = [[INF]*(N+1) for _ in range(N+1)]
for _ in range(R):
    a,b,l = map(int, input().split())
    if l <= M: # 간선이 범위 내이여야 함
        d[a][b] = l
        d[b][a] = l

# 플로이드 와샬 알고리즘
# 노드를 모두 순회하여 배열을 update. (근데 길이가 M이하여야 함)
# k : 거쳐가는 노드, i : 출발노드, j : 도착노드
for k in range(1,N+1):
    for i in range(1,N+1):
        for j in range(1,N+1):
            temp = d[i][k] + d[k][j]
            if temp < d[i][j] and temp <= M:
                d[i][j] = temp

# debug
answer = 0
for i in range(1,N+1):
    # 자기자신 + 연결된 노드의 아이템
    val = items[i]
    val += sum([items[j] for j in range(1, N+1) if j!=i and d[i][j]!=INF])
    # print(i,'번째 노드 기준 = ', val)
    answer = max(answer, val)
print(answer)