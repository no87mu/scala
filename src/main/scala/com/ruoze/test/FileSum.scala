package com.ruoze.test

import java.io.File

object FileSum {


  def main(args: Array[String]): Unit = {
    for ((x,y) <- fSum("d:\\ruozedata_test\\")){
      if(y<0){
        println(x+"-0"+y+".txt")
      }else{
        println(x+"-"+y+".txt")
      }
    }
  }


  def fSum(path:String):scala.collection.mutable.Map[String, Int]={
    val mapData = scala.collection.mutable.Map[String, Int]()
    try{
      val file = new File(path)
      subDir(file).toList.map(x => {
        val p = x.getAbsolutePath
        val sb = new StringBuffer()
        var a = 0;
        var temp = "";
        p.split("\\\\").toList.map(y => {
          if(a < 3 || a == 7){
            sb.append(y).append("\\\\");
          }
          if(a == 5){
            temp = y.substring(10)
            sb.append("data\\\\");
          }
          if(a == 6){
            var t = y.split("=")
            sb.append(t(0)+"=")
            temp = t(1).substring(2)+temp
            sb.append(t(1).substring(2))
            sb.append("\\\\")
          }
          a+=1
        })
        var key = sb.toString + temp
        mapData.get(key) match{
          case Some(b) => mapData += ( key -> b.+(1))
          case None => mapData += ( key -> 1)
        }
      })
    }catch {
      case e1: RuntimeException => println("RuntimeException")
      case e2: Exception => println("Exception")
    }
    mapData
  }

  def subDir(dir:File):Iterator[File]={
    var dirs = dir.listFiles().filter(_.isDirectory)
    var files = dir.listFiles().filter(_.isFile)
    files.toIterator ++ dirs.toIterator.flatMap(subDir _)
  }
}
