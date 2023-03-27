package io.github.needle4k.junit4

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import io.github.needle4k.junit4.testrule.JPATestRule

class JPATestRuleTest {
  @Rule
  @JvmField
  val databaseTestRule = JPATestRule()

  @Test
  fun testEntityManager() {
    Assert.assertNotNull(databaseTestRule.entityManager)
  }
}