package graphqlexample.domain.model.board

import java.util.UUID

data class Board(
    val boardId: UUID,
    val title: String,
    val detail: String,
    val authorId: UUID,
)
