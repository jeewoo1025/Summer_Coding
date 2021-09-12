import math


def timeToMins(val):  # HH:MM -> 분으로
    h, m = val.split(":")
    m = int(m)
    m += int(h) * 60
    return m


def solution(fees, records):
    # 기본 시간, 기본요금
    base_hour = int(fees[0])
    base_fee = int(fees[1])

    # 단위 시간, 단위요금
    add_hour = int(fees[2])
    add_fee = int(fees[3])

    # 입차한 차량들
    IN_list = {}
    result = {}  # k:차량번호, v:total 시간

    for r in records:
        park_time, park_num, flag = r.split(" ")

        park_time = timeToMins(park_time)
        park_num = int(park_num)
        if flag == 'IN':  # 입차
            IN_list[park_num] = park_time  # k:번호,v:입차한시간
        else:  # 출차
            in_time = IN_list[park_num]
            IN_list.pop(park_num)

            # 요금 계산
            # 차량 번호 있니?
            if not park_num in result.keys():
                result[park_num] = 0
            using = (park_time-in_time)
            result[park_num] = using


    # print(park_time, park_num, flag)
    out_time = 1439  # 출차 23:59로 처리함
    for park_num, in_time in IN_list.items():
        park_num = int(park_num)

        if not park_num in result.keys():
            result[park_num] = 0

        using = (out_time - in_time)
        result[park_num] += using

    answer = []
    for park_num, total in sorted(result.items()):
        val = []
        if total <= base_hour:  # 기본 시간 이하
            answer.append(base_fee)
        else:  # 기본 시간 초과
            gap = math.ceil((total - base_hour) / add_hour)
            answer.append(base_fee + gap * add_fee)
    return answer

tmp =[120, 0, 60, 591]
records =["16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"]
print(solution(tmp, records))