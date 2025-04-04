package io.github.needle4k.postconstruct

import io.github.needle4k.annotation.InjectIntoMany
import io.github.needle4k.annotation.ObjectUnderTest
import io.github.needle4k.junit4.NeedleRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PostConstructInjectIntoTest {
  @Rule
  @JvmField
  val needleRule = NeedleRule()

  @Suppress("unused")
  @ObjectUnderTest(postConstruct = true)
  private lateinit var componentWithPostConstruct: ComponentWithPrivatePostConstruct

  @InjectIntoMany
  @ObjectUnderTest(postConstruct = false)
  private lateinit var dependentComponent: DependentComponent

  @Test
  fun testPostConstruct_InjectIntoMany() {
    assertEquals("expect call in postConstruct of ComponentWithPrivatePostConstruct", 1, dependentComponent.counter)
  }
}