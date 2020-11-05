from collections import OrderedDict

dict1={}

fp=open('T4.txt','r')
fp1=open('T4_sorted.txt','w')

for line in fp:

    s1,s2=line.split('\t')
    dict1[s1]=int(s2.strip())


for i in sorted(dict1, key=dict1.get):
    fp1.write(i+' '+str(dict1[i])+'\n')

fp.close()
fp1.close()
