package s.m.mota.comicvineandroidnativeapp.data.datasource.remote

class QueryFieldListBuilder {
    private val fields = mutableSetOf<String>()

    /**
     * Adds a field to the list.
     * @param field The name of the field to add.
     */
    fun addField(field: String): QueryFieldListBuilder {
        fields.add(field)
        return this
    }

    /**
     * Removes a field from the list.
     * @param field The name of the field to remove.
     */
    fun removeField(field: String): QueryFieldListBuilder {
        fields.remove(field)
        return this
    }

    /**
     * Builds the final query string.
     * @return The concatenated string of fields.
     */
    fun build(): String {
        return fields.joinToString(",")
    }

    val ID: QueryFieldListBuilder
        get() = addField("id")

    val NAME: QueryFieldListBuilder
        get() = addField("name")

    val PUBLISHER: QueryFieldListBuilder
        get() = addField("publisher")

    val RESOURCE_TYPE: QueryFieldListBuilder
        get() = addField("resource_type")

    val IMAGE: QueryFieldListBuilder
        get() = addField("image")

    val API_DETAIL_URL: QueryFieldListBuilder
        get() = addField("api_detail_url")

    val SITE_DETAIL_URL: QueryFieldListBuilder
        get() = addField("site_detail_url")

    val COUNT_OF_ISSUES: QueryFieldListBuilder
        //get() = addField("count_of_issues")
        get() = addField("count_of_issue_appearances")

    val COUNT_OF_EPISODES: QueryFieldListBuilder
        get() = addField("count_of_episodes")

    val START_YEAR: QueryFieldListBuilder
        get() = addField("start_year")

    val ALIASES: QueryFieldListBuilder
        get() = addField("aliases")
}