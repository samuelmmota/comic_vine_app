package s.m.mota.comicvineandroidnativeapp.data.model.response

enum class FetchOrderSetting(val key: Int, val jsonName: String) {
    ASCENDING(1, "asc"), DESCENDING(2, "desc");

    companion object {
        val DEFAULT = ASCENDING
        fun fromKey(key: Int): FetchOrderSetting? {
            return entries.firstOrNull { it.key == key }
        }

        fun fromJsonName(jsonName: String): FetchOrderSetting? {
            return entries.firstOrNull { it.jsonName == jsonName }
        }
    }
}