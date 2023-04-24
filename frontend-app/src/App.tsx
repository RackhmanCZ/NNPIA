import './App.css'
import {Provider} from "react-redux";
import store from "./features/store";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Tasks from "./pages/Tasks";
import Header from "./component/ui/Header";
import TaskDetail from "./pages/TaskDetail";

function App() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Header />
                <Routes>
                    <Route path={"/task"} element={<Tasks />} />
                    <Route path={"/task/:id"} element={<TaskDetail />} />
                </Routes>
            </BrowserRouter>
        </Provider>
    )
}

export default App
