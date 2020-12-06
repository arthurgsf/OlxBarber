import React from 'react'
import {Route, Switch, HashRouter} from 'react-router-dom'
import DashBoard from './views/DashBoard'
import Home from './views/Home'

function Rotas() {
    return (
        <HashRouter>
            <Switch>
                <Route exact path="/" component={Home}></Route>
                <Route exact path="/main" component={DashBoard}></Route>
            </Switch>
        </HashRouter>
    )
}
export default Rotas