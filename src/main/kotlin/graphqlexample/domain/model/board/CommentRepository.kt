package graphqlexample.domain.model.board

import graphqlexample.domain.model.user.UserRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CommentRepository {

    companion object {
        private val COMMENT_ID_1 = UUID.fromString("a76080cc-71c5-4b37-827c-726f26633870")
        private val COMMENT_ID_2 = UUID.fromString("c15e3063-9e44-4a7d-a010-3bf0e40dc24f")
        private val COMMENT_ID_3 = UUID.fromString("2145589a-1de8-4ee1-96e5-8ed6dc953966")
        private val COMMENT_ID_4 = UUID.fromString("7ccf31e1-b583-48bd-8aab-1986e0ed3dd8")
    }

    private val comments = listOf(
        Comment(
            commentId = COMMENT_ID_1,
            boardId = BoardRepository.BOARD_ID_1,
            content = "コンテンツ1",
            authorId = UserRepository.USER_ID_3,
        ),
        Comment(
            commentId = COMMENT_ID_2,
            boardId = BoardRepository.BOARD_ID_1,
            content = "コンテンツ2",
            authorId = UserRepository.USER_ID_4,
        ),
        Comment(
            commentId = COMMENT_ID_3,
            boardId = BoardRepository.BOARD_ID_2,
            content = "コンテンツ3",
            authorId = UserRepository.USER_ID_3,
        ),
        Comment(
            commentId = COMMENT_ID_4,
            boardId = BoardRepository.BOARD_ID_2,
            content = "コンテンツ4",
            authorId = UserRepository.USER_ID_4,
        ),

        )

    fun resolveBy(boardIds: Set<UUID>): List<Comment> = this.comments.filter { it.boardId in boardIds }
}
