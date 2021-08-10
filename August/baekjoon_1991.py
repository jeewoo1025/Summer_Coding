N = int(input())
treeNode = {}
for _ in range(N):
    currNode, leftNode, rightNode = input().split()
    treeNode[currNode] = (leftNode, rightNode)


def pre_order():
    # 전위순회 : 루트 - L - R
    # 루트 A(현재 노드)를 스택에 저장
    stack = ['A']

    while stack:
        # 스택 마지막 노드 꺼냄
        curr = stack.pop()
        print(curr, end='')

        # L - R 노드 순으로 스택에 넣기
        left, right = treeNode[curr]

        # 만약 노드가 . 이면 pass
        if right != '.':
            stack.append(right)
        if left != '.':
            stack.append(left)
    print()


def in_order(node):
    # 중위순회 : L - ROOT - R
    if node == '.':
        return
    in_order(treeNode[node][0])
    print(node, end='')     # 자신 출력
    in_order(treeNode[node][1])


def post_order(node):
    # 후위순회 : L - R - ROOT
    if node == '.':
        return
    post_order(treeNode[node][0])
    post_order(treeNode[node][1])
    print(node, end='')


pre_order()
in_order('A')
print()
post_order('A')
print()