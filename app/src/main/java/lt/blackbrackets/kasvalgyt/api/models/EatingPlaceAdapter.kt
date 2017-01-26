package lt.blackbrackets.kasvalgyt.api.models

import android.content.Context
import android.content.Intent

import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.place_item.view.*
import lt.blackbrackets.kasvalgyt.databinding.PlaceItemBinding

/**
 * Created by simonas on 26/01/2017.
 */
class EatingPlaceAdapter(val c : Context) : RecyclerView.Adapter<EatingPlaceAdapter.ViewHolder>() {
    var placeList = mutableListOf<EatingPlace>()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var item = placeList[position]
        var binding : PlaceItemBinding = DataBindingUtil.getBinding(holder!!.itemView)
        item.pagePicture += "&height=480"
        binding.item = item

        holder!!.itemView.openButton.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(item.pageLink)
            c.startActivity(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = PlaceItemBinding.inflate(LayoutInflater.from(c), parent, false).root
        val viewholder = ViewHolder(view)
        return viewholder
    }

    override fun getItemCount() = placeList.size
}