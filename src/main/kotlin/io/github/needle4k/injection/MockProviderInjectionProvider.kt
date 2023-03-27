package io.github.needle4k.injection

import io.github.needle4k.mock.MockProvider
import java.util.function.Supplier

class MockProviderInjectionProvider(supplier: Supplier<MockProvider>) :
  LazyInjectionProvider<MockProvider>(MockProvider::class.java, supplier)