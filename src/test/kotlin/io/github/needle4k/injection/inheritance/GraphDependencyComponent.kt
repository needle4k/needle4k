package io.github.needle4k.injection.inheritance

import io.github.needle4k.MyComponent
import javax.inject.Inject

class GraphDependencyComponent {
  @Inject
  lateinit var component: MyComponent
}