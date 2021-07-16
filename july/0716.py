def solution(record):
    users = {}
    chattings = []
    for r in record:
        tmp = r.split()
        if len(tmp) <= 2:  # Leave
            chattings.append((tmp[1], users[tmp[1]] + "님이 나갔습니다."))
            continue

        if tmp[1] in users.keys():  # tmp[2]로 변경하기
            for idx, c in enumerate(chattings):
                if c[0] == tmp[1]:
                    chattings[idx] = (c[0], c[1].replace(users[tmp[1]], tmp[2]))

        users[tmp[1]] = tmp[2]
        if tmp[0] == "Enter":
            chattings.append((tmp[1], tmp[2] + "님이 들어왔습니다."))

    return [c[1] for c in chattings]


solution(["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"])

# 프로그래머스 124 나라의 숫자
def solution(n):
    if n<=3:
        return '124'[n-1]
    a,b = divmod(n-1, 3)
    return solution(a)+'124'[b]