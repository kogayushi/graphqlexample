package graphqlexample.domain.model.board

import java.util.UUID

data class Comment(
    val commentId: UUID,
    val boardId: UUID,
    val content: String,
    val authorId: UUID,
)
