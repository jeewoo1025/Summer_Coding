# 구간 체크하는 함수
def check(start, end, data):  # 시작, 끝, 리스트
    print(start, '~', end, end='  ')
    # 크기 < N -> 안됨
    if len(data[start:end+1]) < N:
        print("안됨 1")
        return []

    # set(data) != jewerly -> 안됨
    if set(data[start:end+1]) != jewerly:
        print(set(data[start:end+1]), "안됨 2")
        return []

    # Leaf 노드일 때 -> 됨
    if start == end:
        print("리프 노드")
        return [start, end]

    # 된다면 크기 줄이기
    mid = len(data) // 2
    if mid == end:
        return []
    print('mid : ',mid)

    if check(start, mid-1, data):
        print("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ")
        return [start, mid]
    if check(mid, end, data):
        print("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ")
        return [mid+1, end]
    print("None")
    return [start, end]


def solution(gems):
    global jewerly, N  # 전역변수
    jewerly = set(gems)
    N = len(jewerly)

    answer = check(0, len(gems)-1, gems)
    return [answer[0]+1, answer[1]+1]


tmp = [
    ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"],
    ["XYZ", "XYZ", "XYZ"],
    ["AA", "AB", "AC", "AA", "AC"],
    ["ZZZ", "YYY", "NNNN", "YYY", "BBB"]
]

for t in tmp:
    print(t)
    print('정답 :', solution(t),end='\n\n')