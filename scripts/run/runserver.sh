
#Run program 
echo "Submitting"
# ./spark_cluster.sh "EvaluatePrequential -l (SGDLearner -l 0.01 -o LogisticLoss -r ZeroRegularizer) -e (BasicClassificationEvaluator)  " 1>sth.res 2>sth.log
./spark_cluster.sh "EvaluatePrequential -l (trees.HoeffdingTree -l 2 -t 0.05 -g 200 ) -s (FileReader -f ../data/randomtreedata.arff -k 1000)" 1> hdt_1000.res 2> hdt_1000.log 

# ./spark_cluster.sh 1>hdt.res 2>hdt.log
echo "Finish."
#Copy log to local.
# scp nhnguyen@lame11.enst.fr:/cal/homes/nhnguyen/streamdm/hdt.log new.log
# scp nhnguyen@lame11.enst.fr:/cal/homes/nhnguyen/streamdm/hdt.res new.res
