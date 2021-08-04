import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Main from "../page/Main";
import Sub from "../page/Sub";

export default class Routes extends React.Component{

    render() {
        return (
            <Router>
                <Switch>
                    <Route exact path="/" component={Main} />
                    <Route exact path="/sub" component={Sub} />
                </Switch>
            </Router>
        );
    }
}