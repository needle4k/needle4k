package io.github.needle4k.db.operation.h2

import io.github.needle4k.db.JPAInjectorConfiguration
import io.github.needle4k.db.operation.AbstractDeleteOperation
import java.sql.SQLException
import java.sql.Statement

/**
 * Delete everything from the DB: This cannot be done with the JPA, because the
 * order of deletion matters. Instead we directly use a JDBC connection.
 */
@Suppress("unused")
open class H2DeleteOperation(configuration: JPAInjectorConfiguration) : AbstractDeleteOperation(configuration) {
  @Throws(SQLException::class)
  override fun setReferentialIntegrity(enable: Boolean, statement: Statement) {
    val command = "SET REFERENTIAL_INTEGRITY " + enable.toString().uppercase()
    statement.execute(command)
  }
}