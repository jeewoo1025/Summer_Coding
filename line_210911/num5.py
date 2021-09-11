from itertools import combinations


def isSame(user1, user2, size):
    # 2개 길이 차이 > 2이면 안된
    if abs(len(user1)-len(user2)) > size:
        return False

    # 공통된게 n-2개 이상
    gap = 0
    for x in user1:
        x_count = user1.count(x)
        y_count = user2.count(x)
        if x_count != y_count:
            gap += 2

    if gap > size:
        return False
    return True


def isSameEmail(user1, user2):
    # 1. 계정이름 동일하면 같음
    email_c1 = user1.split('@')[0]
    email_c2 = user2.split('@')[0]
    if email_c1 == email_c2:
        return True

    # 2. 계정이름에서 1개의 문자를 삭제하여 전체 이메일 주소 동일하게 만듦
    if isSame(user1, user2, 1):
        return True
    return False


def isGroup(obj, groups):
    if len(groups) <= 0:
        return -1

    for idx in range(len(groups)):
        if obj in groups[idx]:
            return idx
    return -1

def solution(nicks, emails):
    tmp = {nicks[i]:emails[i] for i in range(len(nicks))}
    combi = list(combinations(nicks, 2))
    same_users = {nicks[i]:[] for i in range(len(nicks))}

    # 동일한 닉네임인가?
    for c1,c2 in combi:
        # 기준 c1
        if isSame(c1,c2,2):
            # 동일한 이메일인가?
            if isSameEmail(tmp[c1], tmp[c2]):
                print(c1,'=',c2)
                same_users[c1].append(c2)

    for nick,targets in same_users.items():
        if len(targets)>=1:
            for t in targets:
                nicks.remove(t)
    return len(nicks)

emials =["superman5@abcd.com", "batman432@korea.co.kr", "superman@abcd.com", "supertman5@abcd.com", "superman@erty.net", "batman42@korea.co.kr", "batman432@usa.com"]
nicks =["imhero111", "moneyman", "hero111", "imher1111", "hro111", "mmoneyman", "moneymannnn"]
print(solution(nicks, emials))

