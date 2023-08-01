package io.github.needle4k.plain

import io.github.needle4k.AbstractNeedleSession
import io.github.needle4k.NeedleInjector
import io.github.needle4k.configuration.DefaultNeedleConfiguration
import io.github.needle4k.injection.InjectionConfiguration
import io.github.needle4k.injection.InjectionProvider

/**
 * Needle "extension" without dependencies to testing framework. May be used for non-test classes, e.g. to start
 * injection from a main() method.
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
open class NeedlePlain
@JvmOverloads constructor(
  private val injectee: Any,
  needleInjector: NeedleInjector = NeedleInjector(InjectionConfiguration(DefaultNeedleConfiguration())),
  vararg injectionProviders: InjectionProvider<*>
) : AbstractNeedleSession(needleInjector, *injectionProviders) {

  constructor() : this(NeedleInjector(InjectionConfiguration(DefaultNeedleConfiguration())))

  init {
    addJPAInjectionProvider()
  }

  fun start(): NeedlePlain {
    runBeforeTest(injectee)
    return this
  }

  fun stop(): NeedlePlain {
    runAfterTest()
    return this
  }
}