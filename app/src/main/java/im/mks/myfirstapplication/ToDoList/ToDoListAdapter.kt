package im.mks.myfirstapplication.ToDoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import im.mks.myfirstapplication.R
import im.mks.myfirstapplication.command.PlainCommand
import im.mks.myfirstapplication.command.execute
import kotlinx.android.synthetic.main.todo_list_item.view.*


class TodoListAdapter(
    var props: Props = Props.initial
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {


    /*
    * Props
    */
    class Props(
        val items: Array<Item>
    ) {

        class Item(
            val name: String,
            val description: String,
            val isDone: Boolean,

            val onCheck: PlainCommand
        )

        companion object {
            val initial = Props(arrayOf())
        }
    }

    /*
    * Adapter
    */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(p0)
    }

    override fun getItemCount(): Int {
        return props.items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(props.items[p1])
    }

    class ViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false)) {

        fun bind(item: Props.Item) = with(itemView) {
            todo_title.text = item.name
            check_box.isChecked = item.isDone

            check_box.setOnCheckedChangeListener { _, _ ->
                item.onCheck.execute()
            }
        }
    }

}
