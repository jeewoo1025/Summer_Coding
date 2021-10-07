def solution(n):
    val = 2
    x = n - 1

    while val <= x:
        if x % val == 0:
            # print(val)
            # x = x/val
            return val
        else:
            val += 1
    return val