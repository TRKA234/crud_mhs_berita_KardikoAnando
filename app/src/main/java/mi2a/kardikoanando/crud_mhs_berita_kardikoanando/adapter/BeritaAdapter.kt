package mi2a.kardikoanando.crud_mhs_berita_kardikoanando.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mi2a.kardikoanando.crud_mhs_berita_kardikoanando.R
import mi2a.kardikoanando.crud_mhs_berita_kardikoanando.model.ModelBerita

class BeritaAdapter(
    private val onClick: (ModelBerita) -> Unit
) : ListAdapter<ModelBerita, BeritaAdapter.BeritaViewHolder>(BeritaCallBack) {

    class BeritaViewHolder(itemView: View, val onClick: (ModelBerita) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val imgBerita: ImageView = itemView.findViewById(R.id.imgBerita)
        private val judul: TextView = itemView.findViewById(R.id.judulBerita)
        private val tgl_berita: TextView = itemView.findViewById(R.id.tglBerita)
        val cardBerita: View = itemView.findViewById(R.id.cardBerita)

        //cek berita saat ini
        private var currentBerita: ModelBerita? = null

        init {
            itemView.setOnClickListener {
                currentBerita?.let { it1 -> onClick(it1) }
            }
        }

        fun bind(berita: ModelBerita) {
            currentBerita = berita
            //set ke widget
            judul.text = berita.judul
            tgl_berita.text = berita.tgl_berita

            //untuk menampilkan gambar
            Glide.with(itemView)
                .load("http://10.126.148.105/beritaDb/gambar_berita/" + berita.gambar_berita)
                .centerCrop()
                .into(imgBerita)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_item_berita, parent, false
        )
        return BeritaViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = getItem(position)
        holder.bind(berita)
    }

    object BeritaCallBack : DiffUtil.ItemCallback<ModelBerita>() {
        override fun areItemsTheSame(oldItem: ModelBerita, newItem: ModelBerita): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModelBerita, newItem: ModelBerita): Boolean {
            return oldItem == newItem
        }
    }
}
