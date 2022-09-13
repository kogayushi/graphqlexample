package graphqlexample

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import graphqlexample.domain.model.NotFoundException
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component

@Component
class GraphqlExceptionResolver : DataFetcherExceptionResolverAdapter() {

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {

        if (ex is NotFoundException) {
            return GraphqlErrorBuilder.newError().errorType(ErrorType.NOT_FOUND).message(ex.message).build()
        }

        return super.resolveToSingleError(ex, env)
    }
}
