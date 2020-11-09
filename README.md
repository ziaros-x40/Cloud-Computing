# Cloud-Computing
Project code and materials for Cloud Computing Project Hadoop-MapReduce Tweet Analysis of IPL2020 done under Animesh Chaturvedi

Hadoop was set up to run on Single Node Cluster mode.
Hadoop version 3.2.1 was used in the Project along with compatible JAVA JDK 1.8.0_202
Java Files were compiled using JAVA 8 and JAR files were created using Hadoop's built-in command.

Below are the commands used in order to set up Hadoop, HDFS, JAVA and running Hadoop to obtain Output on Local Disk.

1.  'export PDSH_RCMD_TYPE=ssh' ----connecting to local host without passphrase

2.  'bin/hdfs namenode -format' ----Format the filesystem(existing)

3.  'sbin/start-dfs.sh' ----starting up the namenode and datanode daemon on localhost(http://localhost:9870/)

4.  'export JAVA_HOME=/usr/java/jdk1.8.0_202' ----specifying java version to use

5.  'export PATH=${JAVA_HOME}/bin:${PATH}'  ----specifying path to jar

6.  'export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar'  ----specifying hadoop path to use

7.  'bin/hdfs dfs -mkdir /user'
    'bin/hdfs dfs -mkdir /user/<username>'  ----Make the HDFS directories required to execute MapReduce jobs

8.  'bin/hdfs dfs -mkdir input'
    'bin/hdfs dfs -put INPUTPATH input' ----Copy the input files into the distributed filesystem

9.  'bin/hadoop com.sun.tools.javac.Main geoloc.java' ----compiling geoloc.java file

10. 'jar cf geoloc.jar geoloc*.class'  ----packaging the class files generated into a jar file

11. 'bin/hadoop jar /home/ziaros/project/hadoop-3.2.1/geoloc.jar geoloc input output' ----Running hadoop on the provided jar file. Result stored in output hdfs directory.

12. 'bin/hdfs dfs -get output output' ----copies the output from HDFS to a local directory called output



-Java Directory includes the JAVA code for the Three main MapReduce tasks.

-Python Directory includes the Python code(convert.py) for changing delimitter in Dataset and sort utility(Sort.py) created to Sort the Results obtained from  runnning MapReduce Queries.Also includes the visualizations generated from the sorted Data.

-Input Directory includes the link to the dataset used.

-Output Directory includes raw output(unsorted) and the sorted results obtained from running MapReduce Queries.
