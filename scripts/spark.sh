
$SPARK_HOME/bin/spark-submit \
  --class "org.apache.spark.streamdm.streamDMJob" \
  --master local[2] \
  --driver-memory 4g \
  ../target/scala-2.10/streamdm-spark-streaming_2.10-0.2.jar \
  $1


# spark-submit \
  # --class "org.apache.spark.streamdm.streamDMJob" \
  # --master yarn \
  # streamdm-spark-streaming-_2.10-0.2.jar \
  # $1