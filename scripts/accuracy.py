  # replacements = {'class1,':'0', 'class2,':'1'}
import sys, getopt

def main(argv):
   inputfile = ''
   #outputfile = ''
   try:
      #opts, args = getopt.getopt(argv,"hi:o:",["ifile=","ofile="])
      opts, args = getopt.getopt(argv,"hi:o:",["ifile="])
   except getopt.GetoptError:
      print 'test.py -i <inputfile> '
      sys.exit(2)
   for opt, arg in opts:
      if opt == '-h':
         print 'test.py -i <inputfile>'
         sys.exit()
      elif opt in ("-i", "--ifile"):
         inputfile = arg
      # elif opt in ("-o", "--ofile"):
      #    outputfile = arg
   print 'Input file is "', inputfile
   print 'Output file is "', inputfile

   path = '/Users/minhnguyen/StreamingAlgo/StreamDM/streamDM/scripts/result/'
   inputpath = path + inputfile + '.res'
   outputpath = path  + inputfile + '.accuracy'

   with open(inputpath) as infile, open(outputpath, 'w') as outfile:
    sum = 0.0
    counter = 0
    for line in infile:
        accuracy = ''
        runtime = ''
        # compute the average accuracy
        if 'Accuracy' in line:
            accuracy = line[10:15]
            sum += float(accuracy[:len(accuracy)-1])
            counter += 1
            accuracy += "\n"
        outfile.write(accuracy)
    outfile.write("\n")
    print "Runtime ", runtime
    average = sum/counter
    outfile.write("Average: " +str(average) +"\n")
    outfile.write("Running Time: " + str(runtime))
    print "Average accuracy: " , sum/counter


if __name__ == "__main__":
   main(sys.argv[1:])






