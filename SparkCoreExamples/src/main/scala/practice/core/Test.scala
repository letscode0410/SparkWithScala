package practice.core

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object Test extends  Serializable {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc=new SparkContext("local[2]","test")

    val data=sc.textFile("data/sample.txt")

   val flatValues= data.flatMap(x=>x.split(" "))
    val valuesRDD=flatValues.map(x=>(x,1))
    val finalResults=valuesRDD.reduceByKey((x,y)=>x+y)
    finalResults.collect().foreach(println)
  }

}
