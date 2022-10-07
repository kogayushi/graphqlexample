package graphqlexample.domain.model.board

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class BoardInput(
    @field: Size(max = 10)
    @field: NotEmpty
    var title: String?,
    @field: NotEmpty
    var detail: String?,
) {

    val titleAsNonnull by lazy { this.title!! }
    val detailAsNonnull by lazy { this.detail!! }
}
