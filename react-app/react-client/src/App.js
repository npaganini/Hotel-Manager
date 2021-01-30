import React, { Component } from "react";

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useHistory,
} from "react-router-dom";

import "./App.css";

import Navbar from "./components/Navbar/Navbar";
import Table from "./components/Table/Table";
import DatePicker from "./components/DatePickers/DatePicker";
import Button from "./components/Button/Button";
import Input from "./components/Input/Input";
import Dropdown from "./components/Dropdown/Dropdown";
import Login from "./containers/Login/Login";
import Principal from "./containers/Principal/Principal";
import Reservation from "./containers/Reservation/Reservation";
import CheckIn from "./containers/Check-In/Check-In";
import Registration from './containers/Registration/Registration'

class App extends Component {
  render() {
    return (
      <Router>
        {/* 
        // <Table></Table>
        // <DatePicker label="Desde"></DatePicker>
        //{" "}
        <div>
          // <Button ButtonType="Delete"></Button>
          // <Button ButtonType="Save"></Button>
          // <Button ButtonType="Back"></Button>
          //{" "}
        </div>
        // <Input type="text" label="test"></Input>
        // <Principal></Principal>
        // <Reservation></Reservation> */}
        {/* <Navbar /> */}
        <Switch>
          <Route exact path="/">
            <Principal />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/checkin">
            <CheckIn/>
          </Route>
          <Route path="/reservation">
            <Reservation/>
          </Route>
          <Route path="/registration">
            <Registration/>
          </Route>
        </Switch>
      </Router>
    );
  }
}

export default App;
