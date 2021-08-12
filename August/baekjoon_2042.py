# 백준 2042번
N,M,K = map(int, input().split())

nums = []
for i in range(N):
    nums.append(int(input()))

# tree 생성
S = 1
while S < N:
    S *= 2

# 완전 이진 트리
trees = [0 for _ in range(2*S)]
for i in range(N):
    trees[S+i] = nums[i]   # leaf 노드

for i in range(S-1, 0, -1):
    trees[i] = trees[i*2] + trees[i*2+1]  # 왼쪽 자식 + 오른쪽 자식


# left, right, node : 변동
# queryLeft, queryRight : 변동 X
def query(left, right, node, queryLeft, queryRight):
    # 범위 밖 (=연관없음)
    if right < queryLeft or left > queryRight:
        return 0

    # 범위 안 (=의미 있는 값)
    elif left >= queryLeft and right <= queryRight:
        return trees[node]

    # 범위 걸쳐짐 (=판단 불가)
    mid = (left+right)//2
    leftResult = query(left, mid, node*2, queryLeft, queryRight)
    rightResult = query(mid+1, right, node*2+1, queryLeft, queryRight)
    return leftResult+rightResult


# Indexed Tree 데이터 갱신 방법
# target : 갱신할 leaf 노드 번호
def update(left, right, node, target, diff):
    # 연관 없음
    if target < left or right < target:
        return

    trees[node] += diff
    # print("updated trees[{}] = {}".format(node, trees[node]))

    # leaf 노드가 아니라면
    if left != right:
        mid = (left+right)//2

        # 왼쪽 자식 범위 update
        update(left, mid, node*2, target, diff)

        # 오른쪽 자식 범위 update
        update(mid+1, right, node*2+1, target, diff)


# update & query
for i in range(M+K):
    a, b, c = map(int, input().split())

    if a == 1:    # 수 변경
        # b번째 노드 = trees[S+b-1]
        diff = c - trees[S+b-1]

        # root 노드서부터 Top-down 방식으로 update
        update(1, S, 1, b, diff)
    else:
        result = query(1, S, 1, b, c)
        print(result)
