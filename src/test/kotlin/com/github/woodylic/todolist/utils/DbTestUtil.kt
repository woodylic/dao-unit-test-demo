package com.github.woodylic.todolist.utils

import org.springframework.context.ApplicationContext
import java.sql.ResultSet
import javax.sql.DataSource

class DbTestUtil
{
    companion object
    {
        private lateinit var dataSource: DataSource

        fun use(dataSource: DataSource) : DbTestUtil.Companion
        {
            this.dataSource = dataSource
            return this
        }

        fun executeSQL(sql: String)
        {
            val statement = dataSource.connection.createStatement()
            statement.execute(sql)
        }

        fun executeQuery(querySql: String) : ResultSet
        {
            val statement = dataSource.connection.createStatement()
            return statement.executeQuery(querySql)
        }
    }
}
