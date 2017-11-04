package com.ruoze

import com.ruoze.db.MySQLDbOpt

/**
 * Hello world!
 *
 */
object App {
  def main(args: Array[String]): Unit = {
//    println(MySQLDbOpt.queryDataOfJDBC("admin"))
    println(MySQLDbOpt.queryDataOfDruid("admin"))
  }
}
