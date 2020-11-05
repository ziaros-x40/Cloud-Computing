import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class geoloc {


public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

    String temp = value.toString();
    int counter = 0;
    for( int i=0; i<temp.length(); i++ ) {
        if( temp.charAt(i) == ';' ) {
        counter++;
        }
    }
//Filtering tweets by number of ";"
     if (counter==12){
     String[] s = temp.split(";"); 
     context.write(s[1], one);	// increase count by user location 
        
      }
    }
 }
      



public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

  private IntWritable result = new IntWritable();

  public void reduce(Text key, Iterable<IntWritable> values, Context context)

  throws IOException, InterruptedException {

    int sum = 0;
    for (IntWritable value : values) {
      sum+=value.get();
    }

    result.set(sum);
    context.write(key,result);
     }
  }




public static void runJob(String input, String output) throws Exception {

    Configuration conf = new Configuration();

    Job job = new Job(conf);
    job.setJarByClass(geoloc.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    Path outputPath = new Path(output);
    FileInputFormat.addInputPath(job,new Path(input));
    FileOutputFormat.setOutputPath(job,new Path(output));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

public static void main(String[] args) throws Exception {
    runJob(args[0],args[1]);  //Only Input and Output directories expected.
  }

}
