import math


def convert(n, k):
    answer = ''
    tmp = 'ABCDEFG'

    while n > 0:
        n, rem = divmod(n, k)

        if rem < 10:
            rem_str = str(rem)
        else:
            rem_str = tmp[rem - 10]
        answer = rem_str + answer
    return str(answer)


def isPrime(x):
    if x <= 1:
        return False

    for i in range(2, int(math.sqrt(x)) + 1):
        if x % i == 0:
            return False
    return True


def solution(n,k):
    # 진수 바꾸기
    num = convert(n,k)
    cnt = 0
    for i in num.split('0'):
        if i and isPrime(int(i)):  # 소수인가
            cnt+=1
    return cnt

print(solution(110011,10))
