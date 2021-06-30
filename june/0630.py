# 1차 뉴스클러스터링

import math

def make_set(tmp):
    result = []
    for i in range(1, len(tmp)):
        result.append(tmp[i-1]+tmp[i])
    return result


def jeewoo_solution(str1, str2):
    str1 = str1.lower()
    str2 = str2.lower()

    tmp1 = make_set(str1)
    tmp2 = make_set(str2)

    tmp1 = [i for i in tmp1 if i[0] in "qwertyuiopasdfghjklzxcvbnm" and i[1] in "qwertyuiopasdfghjklzxcvbnm"]
    tmp2 = [i for i in tmp2 if i[0] in "qwertyuiopasdfghjklzxcvbnm" and i[1] in "qwertyuiopasdfghjklzxcvbnm"]

    val = len(tmp1) + len(tmp2)
    same = 0
    for t in tmp1:
        if t in tmp2:
            tmp2.remove(t)
            same += 1

    if same == 0:
        return 1
    val -= same
    result = int((same/val)*65536)
    return result

def solution(str1, str2):
    str1 = [str1[i:i+2].lower() for i in range(0, len(str1)-1) if str1[i:i+2].isalpha()]
    str2 = [str2[i:i+2].lower() for i in range(0, len(str2)-1) if str2[i:i+2].isalpha()]

    gyo = set(str1)&set(str2)
    hap = set(str1)|set(str2)
    print("gyo : ", gyo)
    print("hap : ", hap)

    if len(hap) == 0:
        return 65536

    gyo_sum = sum([min(str1.count(gg), str2.count(gg)) for gg in gyo])
    hap_sum = sum([max(str1.count(hh), str2.count(hh)) for hh in hap])

    return math.floor((gyo_sum/hap_sum)*65536)


# main
str1 = input()
str2 = input()
solution(str1, str2)