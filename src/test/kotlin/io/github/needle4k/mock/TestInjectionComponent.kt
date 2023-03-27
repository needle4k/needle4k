package io.github.needle4k.mock

import io.github.needle4k.MyEjbComponent
import java.net.Authenticator
import javax.ejb.EJB
import javax.inject.Inject

class TestInjectionComponent {
  @Inject
  lateinit var authenticator: Authenticator

  @EJB
  lateinit var ejbComponent: MyEjbComponent
}