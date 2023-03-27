package io.github.needle4k.injection

import io.github.needle4k.db.User
import java.util.*
import javax.inject.Inject

open class UserDao {
  @Inject
  @field:CurrentUser
  lateinit var currentUser: User

  @Inject
  lateinit var user: User

  @Inject
  lateinit var queue: Queue<*>
}