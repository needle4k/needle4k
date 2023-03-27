package io.github.needle4k.mock

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import io.github.needle4k.MyEjbComponent
import io.github.needle4k.annotation.ObjectUnderTest
import io.github.needle4k.junit4.NeedleRule
import java.net.Authenticator
import javax.ejb.EJB
import javax.inject.Inject

@Suppress("CdiInjectionPointsInspection")
class TestMockInjectionTest {
  @Rule
  @JvmField
  val needleRule = NeedleRule()

  @ObjectUnderTest
  private lateinit var objectUnderTest: TestInjectionComponent

  @Inject
  private lateinit var authenticator: Authenticator

  @EJB
  private lateinit var ejbComponent: MyEjbComponent

  @Test
  fun testInjection() {
    Assert.assertNotNull(authenticator)
    Assert.assertEquals(objectUnderTest.authenticator, authenticator)
    Assert.assertNotNull(ejbComponent)
    Assert.assertEquals(objectUnderTest.ejbComponent, ejbComponent)
  }
}