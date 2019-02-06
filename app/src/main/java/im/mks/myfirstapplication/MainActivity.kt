package im.mks.myfirstapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import im.mks.myfirstapplication.Statistics.StatisticsFragment
import im.mks.myfirstapplication.ToDoList.ToDoListFragment
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        // Set up the toolbar.
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_menu_white)
            setDisplayHomeAsUpEnabled(true)
        }

        drawerLayout = drawer_layout.apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setupDrawerContent(nav_view)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.navigation_todo_list -> {
                    replaceFragment(ToDoListFragment.newInstance(), R.id.contentFrame)
                }

                R.id.navigation_statistics -> {
                    replaceFragment(StatisticsFragment.newInstance(), R.id.contentFrame)
                }
            }

            drawerLayout.closeDrawers()
            true
        }
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, @IdRes container: Int) {
    supportFragmentManager.beginTransaction()
        .replace(container, fragment)
        .commit()
}