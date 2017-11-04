package com.ruoze.db.conn

import com.alibaba.druid.pool.{DruidDataSource, DruidDataSourceFactory, DruidPooledConnection}
import com.ruoze.db.config.MySQLConfig

import collection.JavaConversions._

class MySQLDBDruidConnect private{
  private[this] var dds:DruidDataSource = null;

  def MySQLDBDruidConnect() = {
    val propMap:java.util.Map[String, String] = MySQLConfig.MYSQLCONFIG
    dds = DruidDataSourceFactory.createDataSource(propMap).asInstanceOf[DruidDataSource]
  }

  def getDDS():DruidDataSource={
    dds
  }
}

object MySQLDBDruidConnect{
  var mySQLDBDruidConnect = new MySQLDBDruidConnect
  def getInstance():MySQLDBDruidConnect={
    if(mySQLDBDruidConnect.getDDS() == null)
      mySQLDBDruidConnect.MySQLDBDruidConnect()
    mySQLDBDruidConnect
  }

 /* def getConnection():DruidPooledConnection={
    mySQLDBDruidConnect.getDDS().getConnection()
  }*/
}


