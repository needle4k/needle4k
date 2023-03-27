package io.github.needle4k.testng

import io.github.needle4k.AbstractNeedleSession
import io.github.needle4k.NeedleInjector
import io.github.needle4k.configuration.DefaultNeedleConfiguration
import io.github.needle4k.injection.InjectionConfiguration
import io.github.needle4k.injection.InjectionProvider
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

@Suppress("MemberVisibilityCanBePrivate")
abstract class AbstractNeedleTestcase @JvmOverloads constructor(
  needleInjector: NeedleInjector = NeedleInjector(InjectionConfiguration(DefaultNeedleConfiguration())),
  vararg injectionProviders: InjectionProvider<*>
) : AbstractNeedleSession(needleInjector, *injectionProviders) {
  @BeforeMethod
  fun beforeNeedleTestcase() {
    runBeforeTest(this)
  }

  @AfterMethod
  fun afterNeedleTestcase() {
    runAfterTest()
  }
}