import org.junit.Assert.*
import org.junit.Test
import spacexAPI.core.domain.usecase.LaunchDateFormatterUseCase
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class LaunchDateFormatterUseCaseTest {

    private val formatter = LaunchDateFormatterUseCase()

    @Test
    fun `formatDate valid UTC string`() {
        val input = "2025-05-23T14:30:00Z"
        val result = formatter.formatDate(input)
        assertTrue(result.contains("2025-05-23"))
    }

    @Test
    fun `formatDate invalid UTC string format`() {
        val input = "2025/05/23 14:30"
        val result = formatter.formatDate(input)
        assertEquals("Unknown date", result)
    }

    @Test
    fun `formatDate empty string`() {
        val result = formatter.formatDate("")
        assertEquals("Unknown date", result)
    }

    @Test
    fun `formatDate string with only date`() {
        val result = formatter.formatDate("2025-05-23")
        assertEquals("Unknown date", result)
    }

    @Test
    fun `formatDate string with only time`() {
        val result = formatter.formatDate("14:30:00")
        assertEquals("Unknown date", result)
    }

    @Test
    fun `formatDate string with different timezone offset`() {
        val input = "2025-05-23T14:30:00+02:00"
        val result = formatter.formatDate(input)
        assertTrue(result.contains("2025-05-23"))
    }

    @Test
    fun `formatDate string with milliseconds`() {
        val input = "2025-05-23T14:30:00.123Z"
        val result = formatter.formatDate(input)
        assertTrue(result.contains("2025-05-23"))
    }

    @Test
    fun `formatDate string with nanoseconds`() {
        val input = "2025-05-23T14:30:00.123456789Z"
        val result = formatter.formatDate(input)
        assertTrue(result.contains("2025-05-23"))
    }

    @Test
    fun `calculateDaysFromNow future date`() {
        val input = "2025-05-27T15:21:48.323710Z"
        val result = formatter.calculateDaysFromNow(input)
        assertTrue(result > 0)
    }

    @Test
    fun `calculateDaysFromNow past date`() {
        val input = "2025-05-17T15:21:48.323732Z"
        val result = formatter.calculateDaysFromNow(input)
        assertTrue(result < 0)
    }

    @Test
    fun `calculateDaysFromNow current date`() {
        val input = "2025-05-22T15:21:48.323739Z"
        val result = formatter.calculateDaysFromNow(input)
        assertTrue(result == 0L || result == -1L || result == 1L) // slight time diff allowed
    }

    @Test
    fun `calculateDaysFromNow invalid UTC string format for calculation`() {
        val result = formatter.calculateDaysFromNow("not-a-date")
        assertEquals(0, result)
    }

    @Test
    fun `calculateDaysFromNow empty string for calculation`() {
        val result = formatter.calculateDaysFromNow("")
        assertEquals(0, result)
    }

    @Test
    fun `calculateDaysFromNow very distant future date`() {
        val input = "3025-05-23T14:30:00Z"
        val result = formatter.calculateDaysFromNow(input)
        assertTrue(result > 36000)
    }

    @Test
    fun `calculateDaysFromNow very distant past date`() {
        val input = "1025-05-23T14:30:00Z"
        val result = formatter.calculateDaysFromNow(input)
        assertTrue(result < -36000)
    }

    @Test
    fun `calculateDaysFromNow date exactly 24 hours from now`() {
        val inputDateTime = LocalDateTime.now(ZoneOffset.UTC).plusHours(24)
        val input = inputDateTime.format(DateTimeFormatter.ISO_DATE_TIME) + "Z"

        val result = formatter.calculateDaysFromNow(input)

        assertTrue(result in 0..1)
    }

    @Test
    fun `calculateDaysFromNow date just under 24 hours from now`() {
        val input = "2025-05-23T15:20:48.323751Z"
        val result = formatter.calculateDaysFromNow(input)
        assertEquals(0, result)
    }

    @Test
    fun `calculateDaysFromNow date just over 24 hours ago`() {
        val input = "2025-05-21T15:20:48.323758Z"
        val result = formatter.calculateDaysFromNow(input)
        assertEquals(-1, result)
    }

    @Test
    fun `calculateDaysFromNow with different timezone in input string`() {
        val input = "2025-05-23T14:30:00+03:00"
        val result = formatter.calculateDaysFromNow(input)
        assertTrue(result == 0L || result == 1L || result == -1L)
    }
}
