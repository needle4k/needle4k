package io.github.needle4k.postconstruct

import io.github.needle4k.NeedleContext
import io.github.needle4k.annotation.InjectIntoMany
import io.github.needle4k.annotation.ObjectUnderTest
import io.github.needle4k.configuration.DefaultNeedleConfiguration
import io.github.needle4k.configuration.POST_CONSTRUCT_EXECUTE_STRATEGY
import io.github.needle4k.configuration.PostConstructExecuteStrategy
import io.github.needle4k.injection.InjectionConfiguration
import io.github.needle4k.reflection.ReflectionUtil
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import java.lang.reflect.Method
import javax.annotation.PostConstruct

/**
 * @author Heinz Wilming, Alphonse Bendt, Markus Dahm Akquinet AG
 * @author Heinz Wilming, Alphonse Bendt, Markus Dahm Akquinet AG
 * @author Jan Galinski, Holisticon AG (jan.galinski@holisticon.de)
 */
class PostConstructProcessorTest {
  /**
   * a dummy class without init()
   */
  open inner class A

  /**
   * a dummy class with init()
   */
  open inner class B : A() {
    @PostConstruct
    protected fun init() {
      service.run()
    }
  }

  /**
   * used to test postconstruct hierarchy
   */
  open inner class C : B() {
    @PostConstruct
    fun initC() {
      service.run()
    }
  }

  private val needleConfiguration = DefaultNeedleConfiguration()
  private val injectionConfiguration = InjectionConfiguration(needleConfiguration)
  private val postConstructProcessor = injectionConfiguration.postConstructProcessor

  // This Processor test does not use the NeedleRule!
  @ObjectUnderTest(postConstruct = true)
  private val isConfiguredForPostConstructionButDoesNotContainMethod: A = A()

  @InjectIntoMany
  private val service = Mockito.mock(Runnable::class.java)

  // This Processor test does not use the NeedleRule!
  @ObjectUnderTest(postConstruct = true)
  private val isConfiguredForPostConstruction: B = B()

  // This Processor test does not use the NeedleRule!
  @ObjectUnderTest
  private val isNotConfiguredForPostConstruction: B = B()

  // This Processor test does not use the NeedleRule!
  @ObjectUnderTest(postConstruct = true)
  private val instanceAndParentClassHavePostconstructMethods = C()

  @Test
  fun testWithoutPostConstructMethod() {
    val context = NeedleContext(this, needleConfiguration)
    val objectUnderTestAnnotation = getObjectUnderTestAnnotation("isConfiguredForPostConstructionButDoesNotContainMethod")
    context.addObjectUnderTest(
      objectUnderTestAnnotation.id,
      isConfiguredForPostConstructionButDoesNotContainMethod, objectUnderTestAnnotation
    )

    postConstructProcessor.process(context)
    Mockito.verify(service, Mockito.times(0)).run()
  }

  @Test
  fun testWithPostConstructMethod() {
    val context = NeedleContext(this, needleConfiguration)
    val objectUnderTestAnnotation = getObjectUnderTestAnnotation("isConfiguredForPostConstruction")
    context.addObjectUnderTest(
      objectUnderTestAnnotation.id, isConfiguredForPostConstruction,
      objectUnderTestAnnotation
    )
    postConstructProcessor.process(context)
    Mockito.verify(service, Mockito.times(1)).run()
  }

  @Test
  fun testWithPostConstructMethod_NotConfigured() {
    val context = NeedleContext(this, needleConfiguration)
    val objectUnderTestAnnotation = getObjectUnderTestAnnotation("isNotConfiguredForPostConstruction")
    context.addObjectUnderTest(
      objectUnderTestAnnotation.id, isNotConfiguredForPostConstruction,
      objectUnderTestAnnotation
    )
    postConstructProcessor.process(context)
    Mockito.verify(service, Mockito.times(0)).run()
  }

  @Test
  fun shouldCallPostConstructOnInstanceAndParent() {
    val context = NeedleContext(this, needleConfiguration)
    val objectUnderTestAnnotation = getObjectUnderTestAnnotation("instanceAndParentClassHavePostconstructMethods")
    context.addObjectUnderTest(
      objectUnderTestAnnotation.id, instanceAndParentClassHavePostconstructMethods,
      objectUnderTestAnnotation
    )
    postConstructProcessor.process(context)
    Mockito.verify(service, Mockito.times(2)).run()
  }

  @Test
  fun shouldFindTwoPostConstructMethodsForC() {
    val methods: Set<Method> = postConstructProcessor.getPostConstructMethods(C::class.java)

    assertEquals(methods.size.toLong(), 2)
  }

  @Test
  fun shouldExecuteAlways() {
    val context = NeedleContext(this, needleConfiguration)
    val objectUnderTestAnnotation = getObjectUnderTestAnnotation("isNotConfiguredForPostConstruction")
    context.addObjectUnderTest(
      objectUnderTestAnnotation.id, isConfiguredForPostConstruction,
      objectUnderTestAnnotation
    )

    needleConfiguration.configurationProperties[POST_CONSTRUCT_EXECUTE_STRATEGY] = PostConstructExecuteStrategy.ALWAYS.name
    postConstructProcessor.process(context)
    Mockito.verify(service, Mockito.times(1)).run()
  }

  @Test
  fun shouldExecuteNever() {
    val context = NeedleContext(this, needleConfiguration)
    val objectUnderTestAnnotation = getObjectUnderTestAnnotation("isConfiguredForPostConstruction")
    context.addObjectUnderTest(
      objectUnderTestAnnotation.id, isConfiguredForPostConstruction,
      objectUnderTestAnnotation
    )

    needleConfiguration.configurationProperties[POST_CONSTRUCT_EXECUTE_STRATEGY] = PostConstructExecuteStrategy.NEVER.name

    postConstructProcessor.process(context)
    Mockito.verify(service, Mockito.times(0)).run()
  }

  private fun getObjectUnderTestAnnotation(fieldName: String) =
    ReflectionUtil.getField(javaClass, fieldName).getAnnotation(ObjectUnderTest::class.java)
}