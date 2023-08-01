package io.github.needle4k.plain

import io.github.needle4k.annotation.ObjectUnderTest
import io.github.needle4k.db.Person
import io.github.needle4k.db.TransactionHelper
import io.github.needle4k.injection.CustomInjectionTestComponent
import javax.inject.Inject

@Suppress("CdiInjectionPointsInspection")
class NeedlePlainMain {
  private val needlePlain = NeedlePlain(this)

  @ObjectUnderTest
  private lateinit var component: CustomInjectionTestComponent

  @Inject
  private lateinit var transactionHelper: TransactionHelper

  init {
    needlePlain.start()
  }

  fun test() {
    val myEntity = Person().apply { myName = "My Name" }
    transactionHelper.saveObject(myEntity)

    val fromDb = transactionHelper.loadObject(Person::class.java, myEntity.id)

    assert(myEntity !== fromDb)
  }

  fun stop() = needlePlain.stop()

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      val needlePlainMain = NeedlePlainMain()

      assert(needlePlainMain::component.isInitialized) { "Component of $needlePlainMain has not been initialized" }

      needlePlainMain.test()
      needlePlainMain.stop()
    }
  }
}