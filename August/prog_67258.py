def solution(gems):
    jewerly = set(gems)
    MIN, MAX = len(jewerly), len(gems)

    # 크기 MIN ~ MAX까지 조회
    for size in range(MIN, MAX+1):
        start = 0
        while start < len(gems):
            end = start+size-1
            if end >= len(gems):
                break

            # 만약 보석 다 포함하면 break
            #if isOK(start, end, gems):
            #   return [start+1, end+1]
            start += 1

    return [1, len(gems)]


def check(l, r, size):
    if l<=0 or l>=size or r<=0 or r>=size:
        return False
    return True


# 투포인터 문제
def solution2(gems):
    answer = []
    shortest = len(gems)+1

    start,end = 0,0

    jewerly_type = len(set(gems))  # 보석의 총 종류수
    jewerly = {}  # 현재 구간에 포함되어 있는 보석 종류

    while end < len(gems):
        # 현재 보석 종류 update
        if gems[end] not in jewerly:
            jewerly[gems[end]] = 1
        else:
            jewerly[gems[end]] += 1

        end += 1

        # 현재 범위 안 보석종류 = 총 종류수
        if len(jewerly) == jewerly_type:
            # start가 end보다 같을 때까지 증가
            while start < end:
                if jewerly[gems[start]] > 1:  # 2개 이상 있는 거
                    jewerly[gems[start]] -= 1
                    start += 1

                # 기존 구간 최단거리 > 현재 구간거리
                elif shortest > (end-start):
                    shortest = end-start
                    answer = [start+1, end]
                    break
                else:
                    break
    return answer


tmp = ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]
print(solution2(tmp))