package com.techmagnet.travelplanner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.techmagnet.travelplanner.databinding.ItemTripBinding
import com.techmagnet.travelplanner.models.Trip

class TripAdapter(private val trips: List<Trip>,private val onDeleteClick: (Trip) -> Unit) : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {

    inner class TripViewHolder(val binding: ItemTripBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val binding = ItemTripBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val trip = trips[position]
        with(holder.binding) {
            textViewTitle.text = trip.title
            textViewLocation.text = trip.location
            textViewStartDate.text = trip.startDate
            textViewEndDate.text = trip.endDate

            btnDeleteTrip.setOnClickListener {
                onDeleteClick(trip);
                Snackbar.make(root,"Trip Deleted Successfully",Snackbar.LENGTH_SHORT).show();
            }

        }
    }

    override fun getItemCount(): Int = trips.size
}
