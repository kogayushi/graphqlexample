package graphqlexample.presentation.graphql

import graphqlexample.domain.model.board.Board
import graphqlexample.domain.model.board.Comment
import graphqlexample.domain.model.board.CommentRepository
import org.springframework.graphql.data.method.annotation.BatchMapping
import org.springframework.stereotype.Controller

@Controller
class CommentController(
    private val commentRepository: CommentRepository,
) {


    @BatchMapping(field = "comments")
    fun commentsOfBoards(boards: List<Board>): Map<Board, List<Comment>> {
        println("board comments schema mapping")
        val fetched = this.commentRepository.resolveBy(boards.map { it.boardId }.toSet())
        val mapped = boards.associateWith { board ->
            val comments = fetched.filter { comment -> board.boardId == comment.boardId }
            comments
        }
        return mapped
    }
}
