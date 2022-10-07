package graphqlexample.presentation.graphql

import graphqlexample.domain.model.board.Board
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.Many

@Component
class BoardHandler {

    private val logInfoSink: Many<Board> = Sinks.many().replay().all()

    fun addBoard(board: Board) {
        logInfoSink.tryEmitNext(board)
    }

    fun getBoardStream(): Flux<Board> {
        return logInfoSink.asFlux().log()
    }
}
