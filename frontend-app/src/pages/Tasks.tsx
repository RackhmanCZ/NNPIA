import TaskList from "../component/TaskList";
import {Task} from "../data/init-data";
import {useEffect, useState} from "react";
import './Tasks.css';
import {useSelector} from "react-redux";
import {RootState} from "../features/store";

const Tasks = () => {
    const isLoggedIn = useSelector((state: RootState) => state.login.value);

    const [tasks, setTasks] = useState<Task[]>([]);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean | null>(false);

    useEffect(() => {
        console.log(`State changed in ${Tasks.name}: ${isLoggedIn}`);

        if (isLoggedIn) {
            setLoading(true);
            fetchData();
        }
    }, [isLoggedIn]);

    const fetchData = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        let response = null;

        try {
            response = await fetch(`${backendUrl}/task`);
        } catch (e : any) {
            setError(e.message);
            setTasks([]);
        }

        setLoading(false);
        if (response && response.ok) {
            const tasks = await response.json();
            setTasks(tasks);
        }
    };

    return <div className="tasks">
        {error && <div className="alert alert-danger">{error}</div>}
        {loading && <div className="alert alert-danger">loading</div>}
        <TaskList tasks={tasks} />
    </div>
};

export default Tasks;