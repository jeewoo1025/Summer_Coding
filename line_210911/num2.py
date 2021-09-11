
def solution(research, n, k):
    tmp = [0]*(len(research)+1)  # N+1

    for index, words in enumerate(research):
        now = {}
        # 검색어 - 단어 구하기
        for w in words:
            if not w in now.keys():
                now[w] = 1
            else:
                now[w] += 1

        # 최소 k번 이상인 것 있니?
        tmp[index] = {key:value for key,value in now.items() if value>=k}

    keywords = {}
    max_freq = 1

    for i in range(len(research)-n+1):
        #print(i, '~', i+n-1, end=" : ")
        # 연속된 n일 동안 총 2*n*k번 이상이니?
        result = tmp[i]
        if not result:
            continue

        for j in range(i+1, i+n):
            for t1,t2 in tmp[j].items():
                if t1 in result.keys():
                    result[t1] += t2

        for x,y in result.items():
            if y>=2*n*k:
                if x in keywords:
                    keywords[x]+=1
                    max_freq+=1
                else:
                    keywords[x]=1

    if not keywords:
        return "None"

    return sorted([k for k,v in keywords.items() if v>=max_freq])[0]


tm2 =["abaaaa","aaa","abaaaaaa","fzfffffffa"]
print(solution(tm2,2,2))