package io.github.needle4k.injection.cdi

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import io.github.needle4k.annotation.ObjectUnderTest
import io.github.needle4k.junit4.NeedleRule
import javax.enterprise.inject.Instance
import javax.inject.Inject

@Suppress("CdiInjectionPointsInspection")
class InstanceMethodInjectionTest {
  @Rule
  @JvmField
  val needleRule = NeedleRule()

  @ObjectUnderTest
  private lateinit var component: InstanceMethodInjectionBean

  @Inject
  private lateinit var instance: Instance<InstanceTestBean>

  @Inject
  private lateinit var runnableInstances: Instance<Runnable>

  @Suppress("AssertBetweenInconvertibleTypes")
  @Test
  fun testInstanceMethodInjection() {
    assertNotNull(instance)
    assertNotNull(runnableInstances)
    assertNotSame(instance, runnableInstances)
    assertNotNull(component.instance)
    assertSame(instance, component.instance)
  }
}