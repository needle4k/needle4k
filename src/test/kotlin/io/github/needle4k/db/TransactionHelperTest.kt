package io.github.needle4k.db

import org.junit.Assert
import org.junit.Test
import io.github.needle4k.configuration.DefaultNeedleConfiguration

class TransactionHelperTest {
  private val configuration = JPAInjectorConfiguration(DefaultNeedleConfiguration())
  private val objectUnderTest = configuration.transactionHelper
  
  @Test
  fun testLoadAllObjects_WithEntityName() {
    val entity: Person = objectUnderTest.saveObject(Person().apply { myName = "jens" })
    Assert.assertNotNull(entity.id)
    val loadAllObjects: List<Person> = objectUnderTest.loadAllObjects(Person::class.java)
    Assert.assertEquals(1, loadAllObjects.size)
  }

  @Test
  fun testLoadAllObjects_WithDefaultEntityName() {
    val entity: User = objectUnderTest.saveObject(User())
    Assert.assertNotNull(entity.id)
    val loadAllObjects: List<User> = objectUnderTest.loadAllObjects(User::class.java)
    Assert.assertEquals(1, loadAllObjects.size)
  }

  @Test
  fun testLoadAllObjects_EmptyResultList() {
    val loadAllObjects: List<Address> = objectUnderTest.loadAllObjects(Address::class.java)
    
    Assert.assertEquals(0, loadAllObjects.size)
  }

  @Test(expected = IllegalArgumentException::class)
  fun testLoadAllObjects_WithUnknownEntity() {
    objectUnderTest.saveObject(Any())
  }
}