package co.cedesistemas.cakeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.cedesistemas.cakeapp.databinding.ItemProductBinding
import co.cedesistemas.cakeapp.models.ProductResponse
import co.cedesistemas.cakeapp.utils.URL
import com.bumptech.glide.Glide

class ProductsAdapter(val listProducts: List<ProductResponse>) : RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    private lateinit var binding: ItemProductBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemProductBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(listProducts[position])
    }

    //override fun getItemCount(): Int = listProducts.size

    override fun getItemCount(): Int {
        return listProducts.size
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(product: ProductResponse) {
            binding.textViewItem.text = product.name
            val url = "$URL${product.image}"
            Glide.with(itemView.context).load(url).into(binding.imageViewItem)
        }

    }
}
