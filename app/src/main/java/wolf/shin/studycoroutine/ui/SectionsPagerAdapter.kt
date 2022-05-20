package wolf.shin.studycoroutine.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            ImageSearchFragment()
        } else {
            FavoritesFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}