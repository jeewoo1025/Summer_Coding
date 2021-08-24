
def solution(table, languages, preference):
    # 언어(languages)별 딕셔너리 만들기
    langPref = {}
    for i in range(len(languages)):
        langPref[languages[i]] = preference[i]  # 언어 : 선호도

    answer = []
    max_score = -1
    for t in table:
        tmp = t.split(" ")

        lang = tmp.pop(0)
        score = sum([(5-i)*langPref[tmp[i]] for i in range(len(tmp)) if tmp[i] in languages])

        if max_score < score:
            answer = [lang]
        elif max_score == score:
            answer.append(lang)
        max_score = max(max_score, score)

    return sorted(answer)[0]


def perfect_solution(table, languages, preference):
    score = {}
    for t in table:
        for lang, pref in zip(languages, preference):
            if lang in t.split():
                score[t.split()[0]] = score.get(t.split()[0], 0) + (6 - t.split().index(lang)) * pref
    return sorted(score.items(), key=lambda item: [-item[1], item[0]])[0][0]
