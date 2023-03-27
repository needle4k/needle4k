package io.github.needle4k.injection.method

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import io.github.needle4k.annotation.ObjectUnderTest
import io.github.needle4k.injection.method.CurrentUserProvider.currentUser
import io.github.needle4k.junit4.NeedleRule

class MethodInjectionTest {
  @Rule
  @JvmField
  var needleRule = NeedleRule(CurrentUserProvider)

  @ObjectUnderTest
  private lateinit var userDao: MethodInjectionBean

  @Test
  fun testSetterInjection() {
    Assert.assertNotNull(userDao.user)
  }

  @Test
  fun testSetterInjection_Qualifier() {
    val currentUser2 = userDao.currentUser
    Assert.assertSame(currentUser, currentUser2)
  }

  @Test
  fun testMultipleMethodInjection() {
    Assert.assertNotNull(userDao.queue1)
    Assert.assertNotNull(userDao.queue2)
  }
}