# $SPARK_HOME/bin/spark-submit \
#   --class "org.apache.spark.streamdm.streamDMJob" \
#   # --master local[2] \   --> run locally
#   # == This part is to run on clusters
#   --master yarn \     # --> load config file from yarn
#   --deploy-mode cluster \
#   --driver-memory 4g \ #maximum number of virtual cores is 4
#   --executor-memory 2g \
#   --executor-cores 4 \
#   --queue thequeue \
#   --help \	
#   # ../target/scala-2.10/streamdm-spark-streaming-_2.10-0.2.jar \
#   streamdm-spark-streaming-_2.10-0.2.jar \
#   $1


spark-submit \
  --class "org.apache.spark.streamdm.streamDMJob" \
  --master yarn \
  --executor-cores 4 \
  --driver-memory 4g \
  --executor-memory 2g \
  streamDM-spark-streaming-assembly-0.2.jar \
  # --driver-class-path /cal/homes/nhnguyen/streamdm/streamdm-spark-streaming-_2.10-0.2.jar \
  $1