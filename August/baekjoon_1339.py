# 백준 1339번 단어 수학
import sys
import operator

N = int(input())
nums = [[] for _ in range(8)]
alphabet_count = {}

for i in range(N):
    word = sys.stdin.readline().rstrip()
    for j in range(len(word)):
        key = len(word)-1-j

        # i번째 자릿수 추가
        nums[key].append(word[j])

        # 자릿수 합 구하기
        # 이미 존재하는 알파벳 -> 자릿수 더하기
        if word[j] in alphabet_count:
            alphabet_count[word[j]] += pow(10, key)
        else: # 처음 등장
            alphabet_count[word[j]] = pow(10, key)


# 자릿수와 빈도순으로 정렬
alphabet_count = sorted(alphabet_count.items(), key=operator.itemgetter(1), reverse=True)
alphabet = {}  # 알파벳 - 숫자

for idx, alpha in enumerate(alphabet_count):
    # 알파벳에 숫자 할당
    alphabet[alpha[0]] = 9-idx;


# 합 계산하기
answer = 0
for i in range(len(nums)):
    if not nums[i]:
        continue

    val = sum([alphabet[x] for x in nums[i]])
    answer += pow(10, i)*val

print(answer)