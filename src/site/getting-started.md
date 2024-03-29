# Getting Started

In this chapter, a very simple user management application is to be tested using **needle4k**.

## Sample Application

The example consists of two JPA entity classes `User` and `Profile`, with a `@OneToOne` relationship between them and two JPA
components.

### User

```java

@Entity
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @OneToOne(optional = false, cascade = CascadeType.ALL)
  private Profile profile;

  // Getters and setters
  ...
}
```

### Profile

```java

@Entity
public class Profile
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String language;

  // Getters and setters
  ...
}
```

### Data Access Object

Now we add a simple DAO component to access the user data.

```java
public class UserDao
{
  @PersistenceContext
  private EntityManager entityManager;

  public User findBy(final String username, final String password)
  {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);

    Root<User> user = query.from(User.class);

    query.where(
        builder.and(builder.equal(user.get(User_.username), username)),
        builder.equal(user.get(User_.password), password));

    return entityManager.createQuery(query).getSingleResult();
  }

  public List<User> findAll()
  {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);

    return entityManager.createQuery(query).getResultList();
  }
}
```

### Authenticator

To authenticate a user, the application uses an authenticator component which itself depends on the User DAO.

```java
public class Authenticator
{
  @Inject
  private UserDao userDao;

  public boolean authenticate(final String username, final String password)
  {
    User user = userDao.findBy(username, password);

    return user != null ? true : false;
  }
}
```

## Using needle4k with JUnit4

needle4k provides JUnit “Rules” to extend JUnit. Rules are basically wrappers around test methods. They may execute
code before, after or instead of a test method.

The following example demonstrates how to write a simple JUnit test using `NeedleRule`: it scans the test for all fields annotated
with `@ObjectUnderTest` and initializes these tested components by injecting their dependencies. I.e., the `UserDao`
will get the `EntityManager` field injected automatically. Since we call `withJPAInjection()` that entity manager will not be a
mock, but a “real” entity manager.

Supported injections are constructor injection, field injection and method injection.

```java
public class UserDaoTest
{
  @Rule
  public NeedleRule needleRule = new NeedleRule().withJPAInjection();

  @PersistenceContext
  private EntityManager entityManager;

  @ObjectUnderTest
  private UserDao userDao;

  @Test
  public void testFindByUsername() throws Exception
  {
    final User user = new UserTestdataBuilder(entityManager).buildAndSave();
    final User findBy = userDao.findBy(user.getUsername(), user.getPassword());
    assertEquals(user.getId(), findBy.getId());

    final List<User> all = userDao.findAll();
    assertEquals(1, all.size());
  }
}
```

## Using needle4k with JUnit5

Basically it's the same thing, but using "extensions" instead of "rules":

```java
@ExtendWith(JPANeedleExtension.class)
public class UserDaoTest
{
  @PersistenceContext
  private EntityManager entityManager;

  @ObjectUnderTest
  private UserDao userDao;

  @Test
  public void testFindByUsername() throws Exception
  {
    final User user = new UserTestdataBuilder(entityManager).buildAndSave();
    final User findBy = userDao.findBy(user.getUsername(), user.getPassword());
    assertEquals(user.getId(), findBy.getId());

    final List<User> all = userDao.findAll();
    assertEquals(1, all.size());
  }
}
```

## Using needle4k with TestNG

**needle4k** also supports TestNG. There is an abstract test case that may be extended by concrete test classes.

```java
public class UserDaoTest extends AbstractNeedleTestcase
{
  @PersistenceContext
  private EntityManager entityManager;

  @ObjectUnderTest
  private UserDao userDao;

  @BeforeMethod
  public void init()
  {
    addJPAInjectionProvider();
  }

  @Test
  public void testFindByUsername() throws Exception
  {
    final User user = new UserTestdataBuilder(entityManager).buildAndSave();

    User findBy = userDao.findBy(user.getUsername(), user.getPassword());
    Assert.assertEquals(user.getId(), findBy.getId());

    List<User> all = userDao.findAll();
    Assert.assertEquals(1, all.size());
  }
}
```

## Using plain needle4k

It is also possible to use **needle4k** just as an injection framework like CDI without dependencies to a testing framework.

```kotlin
class NeedlePlainMain {
  private val needle = NeedlePlain(this).start()

  @Inject
  private lateinit var transactionHelper: TransactionHelper

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
      val needle = NeedlePlainMain()

      needle.test()
      needle.stop()
    }
  }
}
```
