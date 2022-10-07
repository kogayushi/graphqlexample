package graphqlexample.presentation.graphql.inflastructure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import graphqlexample.domain.model.board.Board
import graphqlexample.presentation.graphql.BoardHandler
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.listener.ChannelTopic


class RedisBoardEventSubscriber(
    private val objectMapper: ObjectMapper,
    private val boardHandler: BoardHandler,
    private val topic: ChannelTopic,
) : MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {
        if (pattern != null && topic.topic == String(pattern)) {
            val board: Board = objectMapper.readValue(message.toString())
            boardHandler.addBoard(board)
        }
    }
}
