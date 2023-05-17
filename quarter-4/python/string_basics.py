# Given a name, return a greeting.
# Input: Matt
# Output: Hello Matt! Have a nice day!
def greet(n):
	return f"Hello {n}! Have a nice day!"

# Given a String, return the word/sentence reversed
# Input: Hello!
# Output: !olleH
def reverse(s):
	return "".join(reversed(s))

# Given a full name return it in the format Last, First
# Input: Nate William Cool
# Output: William Cool, Nate
def last_first(n):
	p = n.split(" ")
	return f"{' '.join(p[1:])}, {p[0]}"

# Given a full name return the proper initials
# Input: Albus Percival Wulfric Brian Dumbledore
# Output: APWBD
def initials(n):
	return "".join(map(lambda s: s[0], n.split(" ")))
	
# Given a String, return the number of vowels it contains (AEIOU)
# Hint: convert the String to upper or lower case first because a != A
# Input: Hello
# Output: 2
def count_vowels(s):
	return len(list(filter(lambda a: a in "aeiou", list(s.lower()))))

if __name__ == "__main__":
	while True:
		n = input("Enter a name (quit to exit): ")
		if n == "quit":
			break
		print(greet(n))
		print(reverse(n))
		print(last_first(n))
		print(initials(n))
		print(count_vowels(n))
