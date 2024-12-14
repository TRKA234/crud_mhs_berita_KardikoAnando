package mi2a.kardikoanando.crud_mhs_berita_kardikoanando.service

import mi2a.kardikoanando.crud_mhs_berita_kardikoanando.model.ResponseBerita
import retrofit2.Call
import retrofit2.http.GET

interface BeritaService {
    @GET("getBerita.php")
    fun getAllBerita() : Call<ResponseBerita>
}