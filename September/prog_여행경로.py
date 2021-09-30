def solution(tickets):
    # 1. 출발-도착 순으로 나눠서 dict에 넣기
    graph = dict()
    for a, b in tickets:
        if a in graph:
            graph[a].append(b)
        else:
            graph[a] = [b]

    # 2. 타겟의 도착지를 역순으로 저장
    for key in graph.keys():
        graph[key].sort(reverse=True)

    # 3. DFS
    stack = ['ICN']
    answer = []

    while stack:
        temp = stack[-1]

        # 3-1. 출발지에 없거나 도착지를 다 돌았으면
        if temp not in graph or len(graph[temp]) == 0:
            answer.append(stack.pop())
        # 3-2. 아니면 갈 곳이 있다란 의미
        else:
            stack.append(graph[temp].pop())

    answer.reverse()
    return answer


tickets = [["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]
solution(tickets)