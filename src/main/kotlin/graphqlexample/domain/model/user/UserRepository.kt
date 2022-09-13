package graphqlexample.domain.model.user

import graphqlexample.domain.model.NotFoundException
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserRepository {

    companion object {
        val USER_ID_1 = UUID.fromString("97122450-66cc-44a1-8ec6-fda1d0d8bce8")
        val USER_ID_2 = UUID.fromString("1134fd00-78cd-4695-8ba0-3168ad4f4d4e")
        val USER_ID_3 = UUID.fromString("89090c72-933f-4dde-8f4a-c22c60ff845b")
        val USER_ID_4 = UUID.fromString("08b52c7f-a70e-4fe5-b0f0-da919e273966")
    }

    private val users = listOf(
        User(
            userId = USER_ID_1,
            name = "ユーザ1",
        ),
        User(
            userId = USER_ID_2,
            name = "ユーザ2",
        ),
        User(
            userId = USER_ID_3,
            name = "ユーザ3",
        ),
        User(
            userId = USER_ID_4,
            name = "ユーザ4",
        ),

        )


    fun resolveBy(userId: UUID): User = this.users.find { it.userId == userId } ?: throw NotFoundException("user not found => $userId")

    fun resolveBy(userIds: Set<UUID>): List<User> = this.users.filter { it.userId in userIds }
}
