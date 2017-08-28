cd $STREAM_DM
sbt assembly
echo "[successful] Done packaging files..."
scp target/scala-2.10/streamDM-spark-streaming-assembly-0.2.jar nhnguyen@lame11.enst.fr:/cal/homes/nhnguyen/streamdm
echo "[successful] Done copying *.jar files..."
cd $STREAM_DM/scripts
scp spark_cluster.sh nhnguyen@lame11.enst.fr:/cal/homes/nhnguyen/streamdm
echo "[successful] Done copying spark_cluster.sh file..."
cd $STREAM_DM/scripts/run
scp runserver.sh nhnguyen@lame11.enst.fr:/cal/homes/nhnguyen/streamdm
echo "[successful] Done copying runserver.sh file..."