package co.cedesistemas.cakeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.cedesistemas.cakeapp.databinding.ItemProductBinding
import co.cedesistemas.cakeapp.listener.ListenerProduct
import co.cedesistemas.cakeapp.models.ProductResponse
import co.cedesistemas.cakeapp.utils.URL
import com.bumptech.glide.Glide


class ProductsAdapter(val listProducts: List<ProductResponse>, val listener: ListenerProduct) : RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    private lateinit var binding: ItemProductBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemProductBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(listProducts[position])
    }

    //override fun getItemCount(): Int = listProducts.size

    override fun getItemCount(): Int {
        return listProducts.size
    }


    inner class MyViewHolder(private val itemProductBinding: ItemProductBinding, private val listenerProduct: ListenerProduct) : RecyclerView.ViewHolder(itemProductBinding.root) {

        fun bindItem(product: ProductResponse) {
            itemProductBinding.textViewItem.text = product.name
            val url = "$URL${product.image}"
            product.bitmap?.let {
                itemProductBinding.imageViewItem.setImageBitmap(it)
            } ?: Glide.with(itemProductBinding.root.context).load(url).into(itemProductBinding.imageViewItem)

            itemProductBinding.root.setOnClickListener {
                // Forma 1, no es muy correcta
              //  itemProductBinding.root.context.startActivity(Intent(itemProductBinding.root.context, DetailProductActivity::class.java))
                listenerProduct.onClickedProduct(product)
            }
        }

    }
}
