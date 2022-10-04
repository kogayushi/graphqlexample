package graphqlexample

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import graphqlexample.domain.model.NotFoundException
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component
import javax.validation.ConstraintViolationException

@Component
class GraphqlExceptionResolver : DataFetcherExceptionResolverAdapter() {

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {

        return super.resolveToSingleError(ex, env)
    }

    override fun resolveToMultipleErrors(ex: Throwable, env: DataFetchingEnvironment): List<GraphQLError>? {

        if (ex is NotFoundException) {
            return listOf(GraphqlErrorBuilder.newError().errorType(ErrorType.NOT_FOUND).message(ex.message).build())
        } else if(ex is ConstraintViolationException) {
            return ex.constraintViolations.map {
                GraphqlErrorBuilder.newError().errorType(ErrorType.BAD_REQUEST).message(it.message)
                    .path(listOf(it.propertyPath.toString())) // パスはtoStringで得るしかないらしい
                    .build()
            }
        }


        return super.resolveToMultipleErrors(ex, env)
    }
}
