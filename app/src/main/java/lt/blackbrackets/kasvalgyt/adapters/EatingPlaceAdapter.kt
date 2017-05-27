package lt.blackbrackets.kasvalgyt.adapters

import android.content.Context
import android.location.Location
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.GONE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.place_item.view.*
import lt.blackbrackets.kasvalgyt.R
import lt.blackbrackets.kasvalgyt.api.models.EatingPlace
import lt.blackbrackets.kasvalgyt.utils.Intents
import kotlin.comparisons.compareBy


/**
 * Created by simonas on 26/01/2017.
 */
class EatingPlaceAdapter(val c : Context, var location: Location) : RecyclerView.Adapter<EatingPlaceAdapter.ViewHolder>() {
    var placeList = listOf<EatingPlace>()
        set(value) {
            field = value.sortedWith(compareBy { it.getDistanceInM(location) })
        }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = placeList[position]
        item.pagePicture += "&height=480"

        if (holder == null) return

        holder.itemView.messageTx.text = item.message
        holder.itemView.pageNameTx.text = item.pageName
        holder.itemView.distanceTx.text = item.getDistanceString(location)

        holder.itemView.openButton.setOnClickListener {
            var pageId = item.pageLink!!.removePrefix("http://facebook.com/")
            Intents.openFbPage(c, pageId)
        }

        if (item.pageLocation != null && item.pageLocation!!.latitude != null && item.pageLocation!!.longitude != null) {
            holder.itemView.locationButton.setOnClickListener {
                Intents.openNav(c, item.pageLocation!!.latitude!!.toFloat(), item.pageLocation!!.longitude!!.toFloat())
            }
        } else {
            holder.itemView.locationButton.visibility = GONE
        }

        if (item.mealImage == null) {
            holder.itemView.mealImageView.visibility = GONE
        }

        if (item.message == null) {
            holder.itemView.messageTx.visibility = GONE
        }

        DrawableCompat.setTint(
                holder.itemView.locationButton.background,
                c.resources.getColor(R.color.colorPrimary)
        )

        holder.itemView.distanceTx.text = item.getDistanceString(location)

        Picasso.with(c).load(item.mealImage).into(holder.itemView.mealImageView)
        Picasso.with(c).load(item.pagePicture).into(holder.itemView.pageImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.place_item, null)
        val viewholder = ViewHolder(view)
        return viewholder
    }

    override fun getItemCount() = placeList.size
}