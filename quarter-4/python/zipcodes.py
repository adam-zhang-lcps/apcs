import csv
from statistics import mean
from operator import itemgetter

all_zip_list = []   # list of dictionaries: lat, long, state, abbrev, county, zip code
state_dictionary = {}  # dictionary of a list of tuples

def read_postal_file():
    with open('assets/us_postal_codes.csv') as my_file:
        allZipData = csv.DictReader(my_file)
         
        for line in allZipData:
            all_zip_list.append(line)
            
read_postal_file()
# print(all_zip_list)

for zip in all_zip_list:
    state_dictionary.setdefault(zip["State"], [])
    state_dictionary[zip["State"]].append((float(zip["Latitude"]), float(zip["Longitude"])))

for state, coords in state_dictionary.items():
    print("%15s, %4d, (%f %f)" % (state, len(coords), mean(map(itemgetter(0), coords)), mean(map(itemgetter(1), coords))))
