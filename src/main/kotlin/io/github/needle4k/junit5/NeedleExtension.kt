package io.github.needle4k.junit5

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import io.github.needle4k.AbstractNeedleSession
import io.github.needle4k.NeedleInjector
import io.github.needle4k.configuration.DefaultNeedleConfiguration
import io.github.needle4k.injection.InjectionConfiguration
import io.github.needle4k.injection.InjectionProvider
import io.github.needle4k.injection.LazyInjectionProvider

@Suppress("MemberVisibilityCanBePrivate", "unused")
open class NeedleExtension
@JvmOverloads constructor(
  needleInjector: NeedleInjector = NeedleInjector(InjectionConfiguration(DefaultNeedleConfiguration())),
  vararg injectionProviders: InjectionProvider<*>
) : AbstractNeedleSession(needleInjector, *injectionProviders), AfterEachCallback, BeforeEachCallback {

  constructor() : this(NeedleInjector(InjectionConfiguration(DefaultNeedleConfiguration())))

  init {
    needleInjector.addInjectionProvider(LazyInjectionProvider(NeedleExtension::class.java) { this })
  }

  override fun beforeEach(context: ExtensionContext) {
    runBeforeTest(context.requiredTestInstance)
  }

  override fun afterEach(context: ExtensionContext) {
    runAfterTest()
  }
}