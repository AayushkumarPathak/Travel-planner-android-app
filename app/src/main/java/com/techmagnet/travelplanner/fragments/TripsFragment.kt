
package com.techmagnet.travelplanner.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.techmagnet.travelplanner.AddTripActivity
import com.techmagnet.travelplanner.databinding.FragmentTripsBinding
import com.techmagnet.travelplanner.db.AppDatabase
import com.techmagnet.travelplanner.repositories.TripRepository
import com.techmagnet.travelplanner.viewmodels.TripViewModel
import com.techmagnet.travelplanner.viewmodels.TripViewModelFactory
import com.techmagnet.travelplanner.adapters.TripAdapter

class TripsFragment : Fragment() {

    private lateinit var binding: FragmentTripsBinding
    private lateinit var tripViewModel: TripViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTripsBinding.inflate(inflater, container, false)

        // ViewModel + Repository setup
        val dao = AppDatabase.getDatabase(requireContext()).tripDao()
        val repository = TripRepository(dao)
        val factory = TripViewModelFactory(repository)
        tripViewModel = ViewModelProvider(this, factory)[TripViewModel::class.java]

        // Observe trips and set to adapter
//        tripViewModel.getAllTrips().observe(viewLifecycleOwner) { trips ->
//            binding.recyclerViewTrips.adapter = TripAdapter(trips)
//        }
        tripViewModel.getAllTrips().observe(viewLifecycleOwner) { trips ->
            binding.recyclerViewTrips.adapter = TripAdapter(trips) { trip ->
                tripViewModel.deleteTrip(trip)
            }
        }

        // FAB click to start AddTripActivity
        binding.fabAddTrip.setOnClickListener {
            val intent = Intent(requireContext(), AddTripActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // Ensure list is updated in case a trip was added
        tripViewModel.refreshTrips()
    }
}
