import math
import random

def get_range():
    while True:
        try:
            r = int(input("enter range: "))
            if r <= 0:
                raise ValueError
            return r
        except ValueError:
            print("invalid range")

def get_guess(t):
    while True:
        try:
            r = int(input(f"enter random # between 0 and {t}: "))
            if r <= 0:
                raise ValueError
            return r
        except ValueError:
            print("invalid guess")

if __name__ == "__main__":
    t = get_range()
    n = random.randint(0, t)
    m = math.ceil(math.log2(t))
    print(f"maxGuess: {m}")
    g = 0
    while True:
        i = get_guess(t)
        if i > n:
            print("too high")
        elif i < n:
            print("too low")
        else:
            print("correct")
            break
        g += 1
        if g >= m:
            print(f"Count exceeded: correct guess was: {n}")
            break
