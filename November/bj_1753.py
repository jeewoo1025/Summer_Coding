from queue import PriorityQueue
import sys

input = sys.stdin.readline

def dijkstra(start):
    pq = PriorityQueue()

    dist[start] = 0
    pq.put((0, start))  # 거리,정점

    while not pq.empty():
        cost, node = pq.get()
        if dist[node] < cost:
            continue

        for nw, nv in graph[node]:
            if dist[nv] > (cost + nw):
                dist[nv] = cost + nw
                pq.put((cost + nw, nv))


V, E = map(int, input().split())
start = int(input())
INF = 10**9
dist = [INF]*(V+1)
graph = [[] for _ in range(V+1)]

for _ in range(E):
    u,v,w = map(int, input().split())
    graph[u].append((w, v))

dijkstra(start)

for i in dist[1:]:
    print(i if i != INF else "INF")