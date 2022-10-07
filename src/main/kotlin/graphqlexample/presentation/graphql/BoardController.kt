package graphqlexample.presentation.graphql

import graphqlexample.domain.model.board.Board
import graphqlexample.domain.model.board.BoardInput
import graphqlexample.domain.model.board.BoardRepository
import graphqlexample.domain.model.user.UserRepository
import org.springframework.graphql.data.method.annotation.*
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import java.util.*
import javax.validation.Valid

@Controller
class BoardController(
    private val boardRepository: BoardRepository,
    private val boardHandler: BoardHandler,
) {

    @QueryMapping
    fun boards(): List<Board> = boardRepository.resolveAll()

    @QueryMapping
    fun board(@Argument boardId: UUID): Board = boardRepository.resolveBy(boardId = boardId)

    @MutationMapping
    fun registerBoard(@Valid @Arguments boardInput: BoardInput): Board {

        val boardId = UUID.fromString("4f4b7126-549e-4521-b454-c7ac61aec0f6")
        val authorId = UserRepository.USER_ID_1

        val board = Board(
            boardId = boardId,
            title = boardInput.title ?: "anan",
            detail = boardInput.detail ?: "enen",
            authorId = authorId,
        )
        boardRepository.save(board)
        return board
    }

    @SubscriptionMapping
    fun updatedBoard(@Argument boardId: UUID): Flux<Board> {
        return boardHandler.getBoardStream().filter {
            it.boardId == boardId
        }
    }
}
