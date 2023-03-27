package io.github.needle4k.db

import io.github.needle4k.injection.InjectionProvider
import io.github.needle4k.injection.InjectionTargetInformation
import javax.persistence.EntityManager

internal class EntityManagerProvider(private val entityManager: EntityManager) : InjectionProvider<EntityManager> {
  override fun verify(injectionTargetInformation: InjectionTargetInformation<*>) =
    injectionTargetInformation.injectedObjectType === EntityManager::class.java

  override fun getInjectedObject(injectionTargetType: Class<*>) = entityManager

  override fun getKey(injectionTargetInformation: InjectionTargetInformation<*>) = EntityManager::class.java
}