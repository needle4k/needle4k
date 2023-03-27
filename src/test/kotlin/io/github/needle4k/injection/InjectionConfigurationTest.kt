package io.github.needle4k.injection

import org.junit.Assert
import org.junit.Test
import io.github.needle4k.configuration.DefaultNeedleConfiguration
import io.github.needle4k.mock.MockProvider
import io.github.needle4k.mock.MockitoProvider

class InjectionConfigurationTest {
  @Test
  fun testCreateMockProvider() {
    val injectionConfiguration = InjectionConfiguration(DefaultNeedleConfiguration())
    val mockProvider: MockProvider = injectionConfiguration.createMockProvider()

    Assert.assertTrue(mockProvider is MockitoProvider)
  }
}