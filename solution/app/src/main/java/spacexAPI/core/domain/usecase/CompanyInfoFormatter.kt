package spacexAPI.core.domain.usecase

object CompanyInfoFormatter {

    fun formatCompanyName(name: String?): String {
        return name ?: "Unknown Company"
    }

    fun formatFounder(founder: String?): String {
        return founder ?: "an unknown founder"
    }

    fun formatFoundedYear(year: Int?): String {
        return year?.toString() ?: "an unknown year"
    }

    fun formatEmployees(employees: Int?): String {
        return employees?.toString() ?: "unknown number of"
    }

    fun formatLaunchSites(count: Int?): String {
        return count?.toString() ?: "unknown number of"
    }

    fun formatValuation(valuation: Long?): String {
        return valuation?.let {
            "%,d".format(it)
        } ?: "an unknown value"
    }

    fun fullSentence(
        name: String?, founder: String?, year: Int?,
        employees: Int?, launchSites: Int?, valuation: Long?
    ): String {
        return "${formatCompanyName(name)} was founded by ${formatFounder(founder)} in ${formatFoundedYear(year)}. " +
                "It has now ${formatEmployees(employees)} employees, ${formatLaunchSites(launchSites)} launch sites, " +
                "and is valued at USD ${formatValuation(valuation)}."
    }
}
