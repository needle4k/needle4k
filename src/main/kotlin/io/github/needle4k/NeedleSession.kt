package io.github.needle4k

import io.github.needle4k.configuration.NeedleConfiguration
import io.github.needle4k.db.JPAInjectionProvider
import io.github.needle4k.db.JPAInjectorConfiguration

interface NeedleSession {
  val needleInjector: NeedleInjector
  val needleConfiguration: NeedleConfiguration
  val needleContext: NeedleContext
  val jpaInjectionProvider: JPAInjectionProvider
  val jpaInjectorConfiguration: JPAInjectorConfiguration
}