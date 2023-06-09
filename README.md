[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.needle4k/needle4k/badge.svg)](https://central.sonatype.com/search?q=needle4k)
<meta name="google-site-verification" content="auOvsU7wt1p_7X07rHySknnRdCmwldQRpABsB-zar_Y" />

![Banner](src/site/images/banner.png)

# Isolated unit testing for Java EE and Spring components

**needle4k is a lightweight framework for testing 
([Java EE/Jakarta EE](https://jakarta.ee/), [Spring Bean](https://spring.io/), e.g.) components in isolation.
Using needle4k it is easy to configure your tests in order to automatically inject mock and real objects into tested components.
needle4k is a [Kotlin-based](https://kotlinlang.org/) rewrite and upgraded version of the well-known
[needle4j](https://needle4j.org/) framework.**

There is a [quickstart project](https://github.com/needle4k/needle4k-quickstart) demonstrating all features.

# Core features

* Automated setup of components annotated with `@ObjectUnderTest`
* Constructor, method and field based dependency injection
* Configurable injection of mock or real objects
* Extensible by providing custom injection providers
* Database testing using [Hibernate](http://www.hibernate.org)
* Optionally clear or setup database before/after each test
* Automatic injection of EntityManager, EntityManagerFactory, EntityTransaction, and DataSource
* As well Java EE as Jakarta EE are supported
* Transaction and reflection utilities
* needle4k can be used with [JUnit4](https://junit.org/junit4/), [JUnit5](https://junit.org/junit5/) or [TestNG](http://testng.org/).
* Pluggable Mock providers: [EasyMock](https://easymock.org/) and [Mockito](https://site.mockito.org/) in particular

# Documentation

For documentation and more examples please refer to the [needle4k site docs](src/site/index.md).

# Getting started

Add the following dependencies to your pom.xml file to get started using **needle4k**:

```xml
<dependency>
    <groupId>io.github.needle4k</groupId>
    <artifactId>needle4k</artifactId>
    <version>4.0.1</version>
    <scope>test</scope>
</dependency>
```

Use this dependency if you are using Jakarta EE &geq; 9:

```xml
<dependency>
    <groupId>io.github.needle4k</groupId>
    <artifactId>needle4k-jakarta</artifactId>
    <version>4.0.1</version>
    <scope>test</scope>
</dependency>
``` 

## Other dependencies

Add to your liking: JUnit4/5, Mockito, AssertJ, or any other testing framework

## Gradle dependency

```gradle
testCompile("io.github.needle4k:needle4k:4.0.1")
```

## Implementing your first JUnit5 test in Java

```java
@ExtendWith(JPANeedleExtension.class) // JUnit5 extension mechanism used
public class UserDaoTest {
  @InjectIntoMany // Mock object will be created and injected everywhere automatically
  private MetricsService metricsService;

  @Inject // Inject components directly into test using standard annotations
  private EntityManager entityManager;

  @ObjectUnderTest // Create tested component and inject dependencies into it and its dependent objects
  private UserDao userDao;

  @Test
  public void testFindByUsername() {
    final User user = new User("demo");
    entityManager.persist(user);
        
    User userFromDb = userDao.findByName("demo");
    assertThat(userFromDb).isEqualTo(user);
  }
}
```
## Implementing your first JUnit5 test in Kotlin

```kotlin
@ExtendWith(JPANeedleExtension::class) // JUnit5 extension mechanism used
class UserDaoTest {
  @InjectIntoMany // Mock object will be created and injected everywhere automatically
  private lateinit var metricsService: MetricsService

  @Inject // Inject components directly into test using standard annotations
  private lateinit var entityManager: EntityManager

  @ObjectUnderTest // Create tested component and inject dependencies into it and its dependent objects
  private lateinit var userDao: UserDao

  @Test
  fun `Find user by name`() {
    val user = User("demo")
    entityManager.persist(user)

    val userFromDb = userDao.findByName("demo")
    assertThat(userFromDb).isEqualTo(user)
  }
}
```

## Licensing

needle4k is licensed under the GNU Lesser General Public License (LGPL) version 2.1 or later.

## Developers

needle4k is based on the [needle4j](https://github.com/needle4j/needle4j) framework originally written by
[Heinz Wilming](mailto:heinz.wilming@akquinet.de),
[Jan Galinski](mailto:jan.galinski@holisticon.de) and [Alphonse Bendt](https://github.com/abendt).

The rewrite has been developed by [Markus Dahm](mailto:markus.dahm@akquinet.de).

## Links

* Source Code:      https://github.com/needle4k/needle4k
* Issue Tracking:   https://github.com/needle4k/needle4k/issues
