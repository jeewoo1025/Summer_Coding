# 퀵 정렬 코드  p.169
arr = [5,7,9,0,3,1,6,2,4,8]

def quick_sort(arr):
    if len(arr) <= 1:
        return arr

    pivot = arr[0]
    tail = arr[1:]

    left_side = [x for x in tail if x <= pivot]
    right_side = [x for x in tail if x > pivot]

    return quick_sort(left_side) + [pivot] + quick_sort(right_side)


# 파이썬 정렬 라이브러리 sorted
arr2 = [('바나나', 10), ('사과', 2), ('고구마', 5)]

def setting(data):
    return -data[1]

result = sorted(arr2, key=setting)


# 2번 위에서 아래로 p.178
def problem2():
    N = int(input())
    num = []
    for _ in range(N):
        num.append(int(input()))

    num = sorted(num, reverse=True)
    for i in range(len(num)):
        print(num[i], end=' ')
    print()


# 3번 성적이 낮은 순서로 학생 출력하기 p.180
def problem3():
    N = int(input())
    students = []
    for _ in range(N):
        tmp = input().split()
        tmp[1] = int(tmp[1])
        students.append((tmp[0], tmp[1]))

    students = sorted(students, key=lambda x:x[1])
    for i in range(len(students)):
        print(students[i][0], end=' ')
    print()


# 4번 두 배열의 원소 교체 p.182
def problem4():
    N,K = map(int, input().split())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))

    A = sorted(A)  # 오름차순
    B = sorted(B, reverse=True)  # 내림차순

    # 디버깅
    print(A)
    print(B)

    cnt = 0
    while cnt < K:
        if A[cnt] < B[cnt]:
            A[cnt], B[cnt] = B[cnt], A[cnt]
            cnt += 1

            # 디버깅
            print(cnt, ':', A)
        else:
            break

    print(sum(A))


# main
problem4()