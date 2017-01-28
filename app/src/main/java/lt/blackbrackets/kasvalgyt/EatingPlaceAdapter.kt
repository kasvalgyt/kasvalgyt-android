package lt.blackbrackets.kasvalgyt

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.place_item.view.*
import lt.blackbrackets.kasvalgyt.databinding.PlaceItemBinding
import android.view.View.GONE
import lt.blackbrackets.kasvalgyt.api.models.EatingPlace
import lt.blackbrackets.kasvalgyt.utils.Intents


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
            var pageId = item.pageLink!!.removePrefix("http://facebook.com/")
            Intents.openFbPage(c, pageId)
        }

        if (item.pageLocation != null && item.pageLocation!!.latitude != null && item.pageLocation!!.longitude != null) {
            holder!!.itemView.locationButton.setOnClickListener {
                Intents.openNav(c, item.pageLocation!!.latitude!!.toFloat(), item.pageLocation!!.longitude!!.toFloat())
            }
        } else {
            holder!!.itemView.locationButton.visibility = GONE
        }

        if (item.mealImage == null) {
            holder!!.itemView.mealImageView.visibility = GONE
        }

        if (item.message == null) {
            holder!!.itemView.messageTx.visibility = GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = PlaceItemBinding.inflate(LayoutInflater.from(c), parent, false).root
        val viewholder = ViewHolder(view)
        return viewholder
    }

    override fun getItemCount() = placeList.size
}