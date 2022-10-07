package graphqlexample.presentation.graphql.inflastructure

import graphqlexample.domain.model.board.Board

interface BoardEventPublisher {
    fun publish(board: Board)
}
