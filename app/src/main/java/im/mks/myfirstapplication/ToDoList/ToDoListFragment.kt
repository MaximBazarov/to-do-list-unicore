package im.mks.myfirstapplication.ToDoList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import im.mks.myfirstapplication.R
import im.mks.myfirstapplication.command.Command
import kotlinx.android.synthetic.main.todo_list_fragment.*

class ToDoListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.todo_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logTheChecker = Command<String> { name ->
            Log.d("(i)", "$name was Checked")
        }

        todo_list.run {
            adapter = TodoListAdapter(
                TodoListAdapter.Props(
                    arrayOf(
                        TodoListAdapter.Props.Item(
                            "test",
                            "test",
                            true,
                            onCheck = logTheChecker.bound("test")
                        ),
                        TodoListAdapter.Props.Item(
                            "CURE THE GRANDPA",
                            "test",
                            false,
                            onCheck = logTheChecker.bound("CURE THE GRANDPA")
                        ),
                        TodoListAdapter.Props.Item(
                            "Eat donut",
                            "test",
                            true,
                            onCheck = logTheChecker.bound("Eat donut")
                        )
                    )
                )
            )

            layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        fun newInstance() = ToDoListFragment()
    }
}