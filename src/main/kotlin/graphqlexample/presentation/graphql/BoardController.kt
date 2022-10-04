package graphqlexample.presentation.graphql

import graphqlexample.domain.model.board.Board
import graphqlexample.domain.model.board.BoardInput
import graphqlexample.domain.model.board.BoardRepository
import graphqlexample.domain.model.user.UserRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.Arguments
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.*
import javax.validation.Valid

@Controller
class BoardController(
    private val boardRepository: BoardRepository,
) {

    @QueryMapping
    fun boards(): List<Board> = boardRepository.resolveAll()

    @QueryMapping
    fun board(@Argument boardId: UUID): Board = boardRepository.resolveBy(boardId = boardId)

    @MutationMapping
    fun registerBoard(@Valid @Arguments boardInput: BoardInput): Board {

        val boardId = UUID.randomUUID()
        val authorId = UserRepository.USER_ID_1
        return Board(
            boardId = boardId,
            title = boardInput.title ?: "anan",
            detail = boardInput.detail ?: "enen",
            authorId = authorId,
        )
    }
}
