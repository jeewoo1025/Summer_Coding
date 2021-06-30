# 프로그래머스 멀쩡한 사각형
import math

# 최대공약수 gcd
def solution(w,h):
    return w*h-(w+h-math.gcd(w,h))