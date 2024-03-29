package io.github.needle4k.db.operation.hsql

import io.github.needle4k.db.JPAInjectorConfiguration
import io.github.needle4k.db.operation.AbstractDeleteOperation
import java.sql.SQLException
import java.sql.Statement

/**
 * Delete everything from the DB: This cannot be done with the JPA, because the
 * order of deletion matters. Instead, we directly use a JDBC connection.
 */
open class HSQLDeleteOperation(configuration: JPAInjectorConfiguration) : AbstractDeleteOperation(configuration) {
  @Throws(SQLException::class)
  override fun setReferentialIntegrity(enable: Boolean, statement: Statement) {
    val databaseMajorVersion = statement.connection.metaData.databaseMajorVersion
    val referentialIntegrity = enable.toString().uppercase()
    val command = if (databaseMajorVersion < 2) "SET REFERENTIAL_INTEGRITY " else "SET DATABASE REFERENTIAL INTEGRITY "

    statement.execute(command + referentialIntegrity)
  }
}