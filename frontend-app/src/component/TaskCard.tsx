import {Task} from "../data/init-data";
import React from "react";

interface Props {
    task: Task;

    onClick: (task: Task) => void;
}

const TaskCard = ({task, onClick} : Props) => {
    const checkboxChangeHandle = (e: React.ChangeEvent<HTMLInputElement>) => {
        e.preventDefault();

        onClick(task);
    };

    return <div>
        <h3>{task.id}:{task.title}</h3>
        <p>{task.description}</p>
        <table>
            <thead>
                <tr>
                    <td>Date of creation</td>
                    <td>Date of update</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>{task.creationDate.toISOString()}</td>
                    <td>{task.updateDate.toISOString()}</td>
                </tr>
            </tbody>
        </table>
        <label htmlFor="myCheckbox">Splněno</label>
        <input type="checkbox" name="myCheckbox" value="myCheckbox" checked={task.done} onChange={checkboxChangeHandle} />
    </div>
};

export default TaskCard;