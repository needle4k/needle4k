package io.github.needle4k.injection.method

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import io.github.needle4k.annotation.ObjectUnderTest
import io.github.needle4k.db.User
import io.github.needle4k.injection.CurrentUser
import io.github.needle4k.injection.method.CurrentUserProvider.currentUser
import io.github.needle4k.junit4.NeedleRule
import javax.inject.Inject

class ConstructorInjectionTest {
  @Rule
  @JvmField
  var needleRule = NeedleRule(CurrentUserProvider)

  @ObjectUnderTest
  private lateinit var constructorBean: ConstructorBean

  @Test
  fun testConstructorInjection() {
    Assert.assertNotNull(constructorBean.user)
    Assert.assertSame(currentUser, constructorBean.currentUser)
  }
}

class ConstructorBean @Inject constructor(val user: User, @param:CurrentUser val currentUser: User)