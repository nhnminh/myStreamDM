replacements = {'0class2': '0,1'}

with open('/Users/minhnguyen/StreamingAlgo/StreamDM/streamDM/data/randomtreedataComplex_new.arff') as infile, open('/Users/minhnguyen/StreamingAlgo/StreamDM/streamDM/data/randomtreedataComplex.arff', 'w') as outfile:
    for line in infile:
        for src, target in replacements.iteritems():
            line = line.replace(src, target)
        outfile.write(line)

