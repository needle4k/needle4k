package io.github.needle4k.injection

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import io.github.needle4k.annotation.ObjectUnderTest
import io.github.needle4k.junit4.NeedleRule

class InjectionFinalClassTest {
  @Rule
  @JvmField
  val needleRule = NeedleRule()

  @ObjectUnderTest
  private lateinit var testClass: InjectionFinalClass

  @Test
  fun testFinal() {

    Assert.assertNull(testClass.string)
  }
}