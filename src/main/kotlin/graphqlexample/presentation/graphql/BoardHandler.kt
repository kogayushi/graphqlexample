package graphqlexample.presentation.graphql

import graphqlexample.domain.model.board.Board
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.Many

@Component
class BoardHandler {

    // many : 複数購読可能
    // multicast : 複数の購読者に配信（購読している全クライアントには配信する）
    // directAllOrNothing : 購読前のものは配信しない
    private val boardSink: Many<Board> = Sinks.many().multicast().directAllOrNothing()

    fun addBoard(board: Board) {
        boardSink.tryEmitNext(board)
    }

    fun getBoardStream(): Flux<Board> {
        return boardSink.asFlux().log()
    }
}
