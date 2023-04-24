import {tasks} from "./data/init-data";
import Tasks from "./pages/Tasks";
import React from "react";

function App() {

  return (
    <div>
        <Tasks tasks={tasks} />
    </div>
  )
}

export default App
