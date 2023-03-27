package io.github.needle4k.injection.inheritance

import io.github.needle4k.MyComponent
import javax.inject.Inject

open class ConstructorInjectionBaseComponent @Inject constructor(val myComponent: MyComponent)