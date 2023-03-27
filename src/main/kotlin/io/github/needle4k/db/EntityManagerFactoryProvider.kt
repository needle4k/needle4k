package io.github.needle4k.db

import io.github.needle4k.injection.InjectionProvider
import io.github.needle4k.injection.InjectionTargetInformation
import javax.persistence.EntityManagerFactory

internal class EntityManagerFactoryProvider(private val entityManagerFactory: EntityManagerFactory) :
  InjectionProvider<EntityManagerFactory> {

  override fun getInjectedObject(injectionTargetType: Class<*>) = entityManagerFactory

  override fun getKey(injectionTargetInformation: InjectionTargetInformation<*>) = EntityManagerFactory::class.java

  override fun verify(injectionTargetInformation: InjectionTargetInformation<*>) =
    injectionTargetInformation.injectedObjectType === EntityManagerFactory::class.java
}