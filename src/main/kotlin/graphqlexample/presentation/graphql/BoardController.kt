package graphqlexample.presentation.graphql

import graphqlexample.domain.model.board.Board
import graphqlexample.domain.model.board.BoardRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class BoardController(
    private val boardRepository: BoardRepository,
) {

    @QueryMapping
    fun boards(): List<Board> = boardRepository.resolveAll()

    @QueryMapping
    fun board(@Argument boardId: UUID): Board = boardRepository.resolveBy(boardId = boardId)
}
