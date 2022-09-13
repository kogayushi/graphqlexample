package graphqlexample.presentation.graphql

import graphqlexample.domain.model.board.Board
import graphqlexample.domain.model.board.Comment
import graphqlexample.domain.model.user.User
import graphqlexample.domain.model.user.UserRepository
import org.springframework.graphql.data.method.annotation.BatchMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class UserController(
    private val userRepository: UserRepository,
) {


    @BatchMapping(field = "author")
    fun authorOfBoard(boards: List<Board>): Map<Board, User> {

        println("boards user batch mapping")
        val fetched = this.userRepository.resolveBy(boards.map { it.authorId }.toSet())
        val mapped = boards
            .map { board ->
                val author = fetched.first { user -> board.authorId == user.userId }
                board to author
            }
            .toMap()
        return mapped
    }

    @BatchMapping(field = "author")
    fun authorOfComment(comments: List<Comment>): Map<Comment, User> {

        println("comments user batch mapping")
        val fetched = this.userRepository.resolveBy(comments.map { it.authorId }.toSet())
        val mapped = comments.associateWith { comment ->
            val author = fetched.first { user -> comment.authorId == user.userId }
            author
        }
        return mapped
    }
}
