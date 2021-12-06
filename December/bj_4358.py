import sys

input = sys.stdin.readline

words = {}
total = 0

while True:
    name = input().rstrip()
    if not name:
        break

    total += 1
    if name in words.keys():
        words[name] += 1
    else:
        words[name] = 1

keys = sorted(words)
for key in keys:
    print('%s %.4f'%(key, words[key]/total*100))