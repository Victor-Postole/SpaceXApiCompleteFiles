package spacexAPI.core.domain.usecase

import org.junit.Test

class CompanyInfoFormatterTest {

    @Test
    fun `formatCompanyName with valid name`() {
        // Arrange
        val input = "SpaceX"

        // Act
        val result = CompanyInfoFormatter.formatCompanyName(input)

        // Assert
        assert(result == "SpaceX")
    }

    @Test
    fun `formatCompanyName with null name`() {
        // Arrange
        val input: String? = null

        // Act
        val result = CompanyInfoFormatter.formatCompanyName(input)

        // Assert
        assert(result == "Unknown Company")
    }

    @Test
    fun `formatCompanyName with empty name`() {
        // Arrange
        val input = ""

        // Act
        val result = CompanyInfoFormatter.formatCompanyName(input)

        // Assert
        assert(result == "")
    }

    @Test
    fun `formatCompanyName with name`() {
        // Arrange
        val input = "Sp@ceX & Co."

        // Act
        val result = CompanyInfoFormatter.formatCompanyName(input)

        // Assert
        assert(result == "Sp@ceX & Co.")
    }

    @Test
    fun `formatCompanyName with very long name`() {
        // Arrange
        val input = "Space Exploration Technologies Corporation ".repeat(10)

        // Act
        val result = CompanyInfoFormatter.formatCompanyName(input)

        // Assert
        assert(result == input)
    }

}