class Edge:
    def __init__(self, a, b, w):
        self.a = a
        self.b = b
        self.weight = w

    def __lt__(self, other):    # 오름차순 정렬
        return self.weight < other.weight


def find(a):
    if parent[a] == a:
        return a
    parent[a] = find(parent[a])
    return parent[a]


# 2개의 노드 합치기
def union(a, b):
    pa = find(a)
    pb = find(b)
    parent[pa] = pb


# 크루스칼 알고리즘
# 입력
V, E = map(int, input().split())
parent = [i for i in range(V+1)]

edges = []
for _ in range(E):
    a,b,w = map(int, input().split())
    edges.append(Edge(a,b,w))

# 간선 기준으로 오름차순 정렬
edges = sorted(edges)

# 가중치가 가장 작은 간선부터 확인
total = 0
for e in edges:
    if find(e.a) != find(e.b):
        union(e.a, e.b)
        total += e.weight
print(total)