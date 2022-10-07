package graphqlexample.presentation.graphql.inflastructure

import graphqlexample.domain.model.board.Board
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic


class RedisBoardPublisher(
    private val redisTemplate: RedisTemplate<String, Board>,
    private val topic: ChannelTopic,
) : BoardEventPublisher {

    override fun publish(board: Board) {
        redisTemplate.convertAndSend(topic.topic, board)
    }
}
