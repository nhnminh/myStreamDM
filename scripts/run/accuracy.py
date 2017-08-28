# replacements = {'class1,':'0', 'class2,':'1'}

with open('/Users/minhnguyen/StreamingAlgo/StreamDM/streamDM/scripts/run/checkHDT9.res') as infile, open('/Users/minhnguyen/StreamingAlgo/StreamDM/streamDM/scripts/run/checkHDT9_accuracy.res', 'w') as outfile:
    sum = 0.0
    counter = 0
    for line in infile:
    	accuracy = ''
    	
        if 'Accuracy ' in line:
        	accuracy = line[9:13]
        	sum += float(accuracy[:len(accuracy)-1])
        	counter += 1
        outfile.write(accuracy)
    print(sum/counter)


