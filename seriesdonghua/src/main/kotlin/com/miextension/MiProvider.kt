package com.miextension

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.LoadResponse.Companion.addEpisode
import com.lagradost.cloudstream3.utils.AppUtils
import org.jsoup.Jsoup

class MiProvider : MainAPI() {

    override var mainUrl = "https://seriesdonghua.com"
    override var name = "MiExtension"
    override val supportedTypes = setOf(TvType.Anime)

    override suspend fun loadMainPage(page: Int): HomePageResponse {
        val url = "$mainUrl/todos-los-donghuas?pag=$page"
        val doc = app.get(url).document

        val items = doc.select("div.row div.item").map {
            val link = it.selectFirst("a")?.attr("href") ?: ""
            val image = it.selectFirst(".img img")?.attr("src")
            val title = it.selectFirst(".bottom-info h5.sf")?.text() ?: "Sin nombre"

            AnimeSearchResponse(
                title,
                link,
                this.name,
                TvType.Anime,
                image
            )
        }

        return HomePageResponse(listOf(HomePageList("Animes", items)))
    }

    override suspend fun load(url: String): LoadResponse {
        val doc = app.get(url).document

        val title = doc.selectFirst("h1, h2, h3")?.text() ?: "Anime"
        val poster = doc.selectFirst("img")?.attr("src")

        val episodes = doc.select(".episodios li").map {
            val link = it.selectFirst("a")?.attr("href") ?: ""
            val epTitle = it.text()
            Episode(link, epTitle)
        }

        return newAnimeLoadResponse(title, url, TvType.Anime) {
            posterUrl = poster
            addEpisodes(episodes)
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {

        val doc = app.get(data).document

        val dailyFrame = doc.selectFirst("div#asura iframe")?.attr("src")
            ?: return false

        callback.invoke(
            ExtractorLink(
                source = "Daily",
                name = "Daily",
                url = dailyFrame,
                referer = mainUrl,
                quality = Qualities.Unknown.value
            )
        )
        return true
    }
}