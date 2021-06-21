import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.ui.activity.DetailActivity
import com.example.movieapp.ui.activity.DetailActivity.Companion.EXTRA_TV
import com.example.movieapp.ui.activity.DetailActivity.Companion.FLAG

class TvAdapter(private val flag: Int) : RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    private var listItem: List<TvEntity> = emptyList()

    fun setItem(item: List<TvEntity>) {
        listItem = item
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovietvBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovietvBinding, flag)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listItem.size

    class ViewHolder(private val binding: ItemMovieBinding, private val flag: Int) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: TvEntity) {
            with(binding) {
                tvMovieTitle.text = item.name
                tvItemDate.text = item.firstAirDate
                tvDescription.text = item.overview
                tvScore.text = (item.voteAverage?.times(10))?.toInt().toString() + "%"
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + item.posterPath)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_TV, item.id.toString())
                    intent.putExtra(FLAG, flag)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}