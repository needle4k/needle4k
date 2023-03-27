package io.github.needle4k.mock

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import io.github.needle4k.annotation.Mock
import io.github.needle4k.junit4.NeedleRule
import javax.persistence.EntityManager

class MockAnnotationTest {
  @Rule
  @JvmField
  val needleRule = NeedleRule()

  @Mock
  private lateinit var entityManagerMock: EntityManager

  @Test
  fun testMockAnnotation() {
    Assert.assertNotNull(entityManagerMock)
  }
}