import {Task} from "../data/init-data";
import TaskCard from "../component/TaskCard";
import {useState} from "react";

interface Props {
    tasks: Array<Task>
}

const Tasks = ({tasks} : Props) => {
    const [taskList, setTaskList] = useState<Array<Task>>(tasks);

    const taskClickHandle = (task: Task) => {
        task.done = !task.done;
        setTaskList([...taskList]);
    }

    return <section>
        <h1>TODO List</h1>
        <h2>Aktuální úkoly</h2>
        {taskList.filter(t => !t.done).map( t => <TaskCard key={t.id} task={t} onClick={taskClickHandle} />)}
        <h2>Splněné úkoly</h2>
        {taskList.filter(t => t.done).map( t => <TaskCard key={t.id} task={t} onClick={taskClickHandle} />)}
    </section>
};

export default Tasks;