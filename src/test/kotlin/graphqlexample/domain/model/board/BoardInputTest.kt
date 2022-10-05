package graphqlexample.domain.model.board

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.validation.Validation
import javax.validation.Validator

private class BoardInputTest {

    private val validator: Validator = Validation.buildDefaultValidatorFactory().getValidator()

    @Test
    fun `if all of fields are null, violations size is 2`() {

        val target = BoardInput(
            title = null,
            detail = null,
        )
        val violations = validator.validate(target)

        assertThat(violations.size).isEqualTo(2)
    }

    @Test
    fun `if all of fields are empty, violations size is 2`() {

        val target = BoardInput(
            title = "",
            detail = "",
        )
        val violations = validator.validate(target)

        assertThat(violations.size).isEqualTo(2)
    }
}
