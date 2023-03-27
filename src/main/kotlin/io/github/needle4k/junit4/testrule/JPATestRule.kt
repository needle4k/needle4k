package io.github.needle4k.junit4.testrule

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import io.github.needle4k.configuration.DefaultNeedleConfiguration
import io.github.needle4k.configuration.NeedleConfiguration
import io.github.needle4k.db.JPAInjectionProvider
import io.github.needle4k.db.JPAInjectorConfiguration
import javax.persistence.EntityManager

class JPATestRule @JvmOverloads constructor(
  needleConfiguration: NeedleConfiguration = DefaultNeedleConfiguration(),
) : TestRule {
  private val jpaInjectionProvider = JPAInjectionProvider(JPAInjectorConfiguration(needleConfiguration))

  val entityManager: EntityManager get() = jpaInjectionProvider.configuration.entityManager

  override fun apply(base: Statement, description: Description): Statement {
    return object : Statement() {
      override fun evaluate() {
        try {
          jpaInjectionProvider.before()
          base.evaluate()
        } finally {
          jpaInjectionProvider.after()
        }
      }
    }
  }
}