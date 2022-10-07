package graphqlexample.presentation.graphql.inflastructure

import com.fasterxml.jackson.databind.ObjectMapper
import graphqlexample.domain.model.board.Board
import graphqlexample.presentation.graphql.BoardHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.*


@Configuration
class RedisConfiguration(
    private val objectMapper: ObjectMapper,
    private val boardHandler: BoardHandler,
) {

    @Bean
    fun redisContainer(): RedisMessageListenerContainer? {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(jedisConnectionFactory())
        container.addMessageListener(messageListener(), topic())
        return container
    }

    @Bean
    fun messageListener(): MessageListenerAdapter {
        return MessageListenerAdapter(redisBoardEventSubscriber())
    }


    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        return JedisConnectionFactory()
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("board")
    }

    @Bean
    fun redisPublisher(): BoardEventPublisher {
        return RedisBoardPublisher(redisTemplate(), topic())
    }

    @Bean
    fun redisBoardEventSubscriber(
    ): RedisBoardEventSubscriber {
        return RedisBoardEventSubscriber(
            objectMapper = objectMapper,
            boardHandler = boardHandler,
            topic = topic()
        )
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Board> {
        val template = RedisTemplate<String, Board>()
        template.setConnectionFactory(jedisConnectionFactory())
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.afterPropertiesSet()
        return template
    }
}
