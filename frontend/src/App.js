import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Links from './Links';
import LinkEdit from './LinkEdit';

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Links}/>
                    <Route path='/links/:id' component={LinkEdit}/>
                </Switch>
            </Router>
        )
    }
}

export default App;