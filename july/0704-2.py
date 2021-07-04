arr = [5,4,3,2,1]
obj = [i for i in range(1,len(arr)+1)]
k = 2
result = 0

for val in range(len(arr), 0, -1):
    # 현재 값이 자신의 위치에 갈 때까지 반복임
    idx = arr.index(val)
    if obj == arr:
        break

    print("val : ", val)
    for i in range(k, -k, -1):
        if i == 0:
            continue
        if (idx+i) < 0 or (idx+i) >= len(arr):
            continue

        if arr[idx+i] < val:
            arr[idx+i], arr[idx] = arr[idx], arr[idx+i]
            result += 1

            # debug
            print("arr : ", arr)

            if arr[val-1] == val:
                break

print("arr : ", arr)
print('result : ', result)

