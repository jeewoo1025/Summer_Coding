from sys import stdin

def main():
    #T = int(input())
    T = int(stdin.readline())

    for cnt in range(T):
        N = int(input())

        camels = list(map(int, input().split()))
        camels = sorted(camels)

        answer = 0
        if (N <= 1):
            answer = camels[0]
        elif (N <= 2):
            answer = camels[1]
        else:
            # 초기화
            answer = camels[1]
            dest = [camels.pop(0) for _ in range(2)]

            while len(camels) > 0:
                # dest에서 최소값 옮기기
                min_idx = dest.index(min(dest))
                answer += dest[min_idx]
                camels.append(dest.pop(min_idx))

                camels.sort(reverse=True)

                move_cnt = 2 if len(camels) >= 2 else 1
                moves = [camels.pop(0) for _ in range(move_cnt)]

                answer += max(moves)
                dest += moves

        print("#{} {}".format((cnt + 1), answer))

main()

#with open("sample_input.txt", 'r') as file:
#    lines = file.readlines()
#    for l in lines:
#        print(l.strip())