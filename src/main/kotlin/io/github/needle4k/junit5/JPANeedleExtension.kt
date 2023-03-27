package io.github.needle4k.junit5

import io.github.needle4k.NeedleInjector
import io.github.needle4k.configuration.DefaultNeedleConfiguration
import io.github.needle4k.injection.InjectionConfiguration
import io.github.needle4k.injection.InjectionProvider
import io.github.needle4k.injection.LazyInjectionProvider

@Suppress("MemberVisibilityCanBePrivate", "unused")
open class JPANeedleExtension
@JvmOverloads constructor(
  needleInjector: NeedleInjector = NeedleInjector(InjectionConfiguration(DefaultNeedleConfiguration())),
  vararg injectionProviders: InjectionProvider<*>
) : NeedleExtension(needleInjector, *injectionProviders) {

  constructor() : this(NeedleInjector(InjectionConfiguration(DefaultNeedleConfiguration())))

  init {
    addJPAInjectionProvider()
    needleInjector.addInjectionProvider(LazyInjectionProvider(JPANeedleExtension::class.java) { this })
  }
}