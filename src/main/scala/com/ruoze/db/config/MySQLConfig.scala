package com.ruoze.db.config

object MySQLConfig {
  val MYSQLCONFIG = collection.mutable.Map(
    "driver" -> "com.mysql.jdbc.Driver",
    "url" -> "jdbc:mysql://localhost:3306/scala?autoReconnect=true",
    "username" -> "root",
    "password" -> "root"
  )
}
