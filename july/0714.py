def solution(people, limit):
    people.sort(reverse=True)
    answer = 0
    print(people)

    left, right = 0, len(people) - 1
    while left <= right:
        print("left : {}, right : {}".format(left, right))
        if people[left] + people[right] > limit:
            answer += 1
            left += 1
        else:
            left += 1
            right -= 1
            answer += 1
    print(answer)
    return answer


# Minimum Spanning Tree
# Prim's Algorithm
# Y로부터 가장 가까운 vertex를 찾기
def solution(n, costs):
    costs = sorted(costs, key=lambda x: x[2])
    answer = 0

    routes = set([costs[0][0]])
    while len(routes) != n:
        for i, cost in enumerate(costs):
            if cost[0] in routes and cost[1] in routes:
                continue

            if cost[0] in routes or cost[1] in routes:
                routes.update([cost[0], cost[1]])
                print(routes)
                answer += cost[2]
                costs[i] = [-1, -1, -1]
                break

    return answer










