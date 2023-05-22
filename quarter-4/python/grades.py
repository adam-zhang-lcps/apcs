def count_grades(grades):
    d = dict()
    for g in grades:
        if g in d:
            d[g] += 1
        else:
            d[g] = 1
    return d

def process_grades(grades):
    for k, v in reversed(sorted(grades.items(), key = lambda a: a[1])):
        print(f"{k}: {v}")

process_grades(count_grades(['B', 'C', 'A', 'A', 'D', 'B', 'B', 'A', 'C']))
