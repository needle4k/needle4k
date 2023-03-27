package io.github.needle4k.injection.method

import io.github.needle4k.db.User
import io.github.needle4k.injection.CurrentUser
import io.github.needle4k.injection.InjectionProvider
import io.github.needle4k.injection.InjectionTargetInformation

object CurrentUserProvider : InjectionProvider<User> {
  val currentUser: User = User()

  override fun verify(injectionTargetInformation: InjectionTargetInformation<*>) = injectionTargetInformation.getAnnotation(CurrentUser::class.java) != null

  override fun getInjectedObject(injectionTargetType: Class<*>) = currentUser

  override fun getKey(injectionTargetInformation: InjectionTargetInformation<*>) = CurrentUser::class.java
}