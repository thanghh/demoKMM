package org.oto.theory.repository

import org.oto.theory.model.Manga
import org.oto.theory.network.APIClient

class MangaRepository(
    private val apiClient: APIClient
) {
    suspend fun getListManga(): List<Manga> {
        return apiClient.fetchMangaList()
    }
}