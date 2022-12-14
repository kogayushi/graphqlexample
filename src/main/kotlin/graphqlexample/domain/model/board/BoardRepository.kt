package graphqlexample.domain.model.board

import graphqlexample.domain.model.NotFoundException
import graphqlexample.domain.model.user.UserRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class BoardRepository {

    companion object {
        val BOARD_ID_1 = UUID.fromString("098cb97b-3816-4342-817a-9c54656cf0a2")
        val BOARD_ID_2 = UUID.fromString("1be9194e-91de-44b9-9e77-81fc8c144d69")
    }

    private val boards = listOf(
        Board(
            boardId = BOARD_ID_1,
            title = "タイトル1",
            detail = "詳細1",
            authorId = UserRepository.USER_ID_1,
        ), Board(
            boardId = BOARD_ID_2,
            title = "タイトル2",
            detail = "詳細2",
            authorId = UserRepository.USER_ID_2,
        )
    )

    fun resolveAll(): List<Board> = boards

    fun resolveBy(boardId: UUID): Board = this.boards.find { it.boardId == boardId } ?: throw NotFoundException("board not found => $boardId")
}
