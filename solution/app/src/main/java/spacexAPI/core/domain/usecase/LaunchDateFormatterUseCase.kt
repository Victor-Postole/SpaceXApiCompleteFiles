package spacexAPI.core.domain.usecase


import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class LaunchDateFormatterUseCase {

    fun formatDate(utcString: String): String {
        val formatterIn = DateTimeFormatter.ISO_DATE_TIME
        val formatterOut = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm")
        return try {
            val date = LocalDateTime.parse(utcString, formatterIn)
            date.format(formatterOut)
        } catch (e: Exception) {
            "Unknown date"
        }
    }



    fun calculateDaysFromNow(utcString: String): Long {
        return try {
            val instant = Instant.parse(utcString)
            val launchDate = instant.atZone(ZoneOffset.UTC).toLocalDate()
            val now = LocalDate.now(ZoneOffset.UTC)
            ChronoUnit.DAYS.between(now, launchDate)
        } catch (e: Exception) {
            0
        }
    }
}
