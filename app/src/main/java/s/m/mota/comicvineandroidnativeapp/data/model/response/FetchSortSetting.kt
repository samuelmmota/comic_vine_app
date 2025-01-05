package s.m.mota.comicvineandroidnativeapp.data.model.response

enum class FetchSortSetting(val key: Int, val jsonName: String) {
    ID(1, "id"), DATE_ADDED(2, "date_added"), DATE_LAST_UPDATED(3, "date_last_updated");

    companion object {
        val DEFAULT = ID
        fun fromKey(key: Int): FetchSortSetting? {
            return entries.firstOrNull { it.key == key }
        }

        fun fromJsonName(jsonName: String): FetchSortSetting? {
            return entries.firstOrNull { it.jsonName == jsonName }
        }
    }
}