# 백준 2458번
# 플로이드 워셜 알고리즘 : 그래프에서 가능한 모든 쌍에 대해 최단 거리를 구하는 알고리즘
# O(N^3)
MAX = 999999999

# N : 학생들의 수(=노드 갯수), M : 비교 횟수(=간선 갯수)
N, M = map(int, input().split())

# 1. 그래프 초기화
graph = [[MAX for j in range(N+1)] for i in range(N+1)]

# 2. 입력
for i in range(M):
    a, b = map(int, input().split())   # a < b
    graph[a][b] = 1

# 3. 플로이드 워셜 진행
for m in range(1, N+1):  # 가운데 노드
    for s in range(1, N+1):   # 시작 노드
        for e in range(1, N+1):   # 마지막 노드
            # 시작 ~ 마지막 > 시작 ~ 가운데 + 가운데 + 끝 -> 갱신
            if graph[s][e] > graph[s][m] + graph[m][e]:
                graph[s][e] = graph[s][m] + graph[m][e]


# 4. 모든 학생과의 비교가 가능한 경우
#      == 거리가 INF가 아닌 학생의 수가 N-1인 경우
answer = 0
for i in range(1, N+1):
    cnt = 0
    for j in range(1, N+1):
        if graph[i][j] != MAX or graph[j][i] != MAX:
            cnt += 1

    if cnt == N-1:
        answer += 1

print(answer)


# 출력
def printGraph():
    for i in range(1, N+1):
        for j in range(1, N+1):
            if graph[i][j] >= MAX:
                print('INF', end=' ')
            else:
                print(graph[i][j], end=' ')
        print()
