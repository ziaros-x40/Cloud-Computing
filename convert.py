import csv


with open("IPL2020_Tweets.csv", mode="rU") as infile:
    reader = csv.reader(infile, dialect="excel")    
    with open("temp2.csv", mode="w") as outfile:
        writer = csv.writer(outfile, delimiter=';')
        writer.writerows(reader)
