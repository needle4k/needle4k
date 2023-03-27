package io.github.needle4k.junit5

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import io.github.needle4k.MyEjbComponent
import io.github.needle4k.junit4.AbstractNeedleTest
import javax.ejb.SessionContext
import javax.inject.Inject
import javax.persistence.EntityManagerFactory

@Suppress("CdiInjectionPointsInspection")
@ExtendWith(value = [NeedleExtension::class])
class NeedleJUnit5Test : AbstractNeedleTest() {
  @Inject
  private lateinit var needle: NeedleExtension

  @Test
  override fun testBasicInjection() {
    super.testBasicInjection()
  }

  @Test
  fun testMock() {
    assertNotNull(needle.getInjectedObject(MyEjbComponent::class.java))
    assertNotNull(needle.getInjectedObject(SessionContext::class.java))
    assertNotNull(needle.getInjectedObject("queue1"))
    assertNotNull(needle.getInjectedObject("queue2"))
    assertNotNull(needle.getInjectedObject(EntityManagerFactory::class.java))
  }
}