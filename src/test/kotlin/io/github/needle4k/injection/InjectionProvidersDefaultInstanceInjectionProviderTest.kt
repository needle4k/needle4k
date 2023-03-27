package io.github.needle4k.injection

import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test
import io.github.needle4k.MyConcreteComponent
import io.github.needle4k.junit4.NeedleRule
import javax.inject.Inject

/**
 * moved from original package to avoid
 */
@Suppress("CdiInjectionPointsInspection")
class InjectionProvidersDefaultInstanceInjectionProviderTest {
  private val instance: MyConcreteComponent = MyConcreteComponent()

  @Rule
  @JvmField
  val needle: NeedleRule = NeedleRule(InjectionProviders.providerForInstance(instance))

  @Inject
  private lateinit var injectedInstance: MyConcreteComponent

  @Test
  fun shouldInjectInstanceA() {
    assertSame(injectedInstance, instance)
  }
}