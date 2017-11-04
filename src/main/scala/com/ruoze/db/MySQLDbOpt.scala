package com.ruoze.db

import com.alibaba.druid.pool.DruidPooledConnection
import com.ruoze.db.conn.{MySQLDBConnect, MySQLDBDruidConnect}

import scala.collection.mutable.{ListBuffer, Map}

object MySQLDbOpt {
def queryDataOfJDBC(usercode:String) : ListBuffer[Map[String, String]] = {
    val conn = MySQLDBConnect.getConnection()
    val resultList = ListBuffer[Map[String, String]]()
    try{
      val statement = conn.createStatement();
      val sql =
        s"""
           |select *
           |from sys_user
           |where usercode='$usercode'
         """.stripMargin
      println(sql)
      val resultSet = statement.executeQuery(sql)
      while(resultSet.next()){
        val id = resultSet.getInt("id")
        val usercode = resultSet.getString("usercode")
        val username = resultSet.getString("username")
        var map = Map[String, String]()
        map += ("id"->id.toString)
        map += ("usercode" -> usercode)
        map += ("username" -> username)
        resultList += map
      }

    }catch {
      case e : Throwable => e.printStackTrace()
      case e : Exception => e.printStackTrace()
    }finally {
      MySQLDBConnect.closeConnection(conn)
    }
    resultList;
  }

  def queryDataOfDruid(usercode:String) : ListBuffer[Map[String, String]] = {
    val dds = MySQLDBDruidConnect.getInstance()
    val conn:DruidPooledConnection = dds.getDDS().getConnection()

    val resultList = ListBuffer[Map[String, String]]()
    try{
      val statement = conn.createStatement();
      val sql =
        s"""
           |select *
           |from sys_user
           |where usercode='$usercode'
         """.stripMargin
      println(sql)
      val resultSet = statement.executeQuery(sql)
      while(resultSet.next()){
        val id = resultSet.getInt("id")
        val username = resultSet.getString("username")
        var map = Map[String, String]()
        map += ("id"->id.toString)
        map += ("usercode" -> usercode)
        map += ("username" -> username)
        resultList += map
      }

    }catch {
      case e : Throwable => e.printStackTrace()
      case e : Exception => e.printStackTrace()
    }finally {
      if(conn != null)
        conn.close()
    }
    resultList;
  }
}
