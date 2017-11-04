package com.ruoze.db.conn

import java.sql.{Connection, DriverManager}

import com.ruoze.db.config.MySQLConfig

object MySQLDBConnect {


  def getConnection():Connection = {
    Class.forName(MySQLConfig.MYSQLCONFIG("driver"))
    DriverManager.getConnection(MySQLConfig.MYSQLCONFIG("url"),MySQLConfig.MYSQLCONFIG("username"),MySQLConfig.MYSQLCONFIG("password"))
  }

  def closeConnection(connection: Connection):Unit={
    try{
      if(connection != null){
        connection.close();
      }
    }catch {
      case e:Throwable =>{
        e.printStackTrace();
      }
    }
  }
}
